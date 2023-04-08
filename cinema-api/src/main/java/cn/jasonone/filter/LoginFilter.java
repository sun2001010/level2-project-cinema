package cn.jasonone.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@WebFilter("/*")
public class LoginFilter implements Filter {
    private Set<String> whiteList;

    private String jwtPassword;
    @Override
    public void init(FilterConfig fc) throws ServletException {
        ServletContext sc = fc.getServletContext();
        // 获取web.xml中配置的参数
        String loginExclude = sc.getInitParameter("login_exclude");
        // 将配置的参数转换为Set
        String[] split = loginExclude.split("\\n");
        whiteList = new HashSet<>();
        for (String s : split) {
            whiteList.add(s.trim());
        }
        this.jwtPassword = sc.getInitParameter("jwt_password");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        // 去除contextPath
        requestURI = requestURI.substring(req.getContextPath().length());
        // 如果是白名单中的地址，直接放行
        if(whiteList.contains(requestURI)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            // 获取请求头中的token
            String token = req.getHeader("hm-token");
            Map<String, Object> result = new HashMap<>();
            // 如果token为不空，且验证通过
            if(!StrUtil.isBlank(token) && JWTUtil.verify(token, jwtPassword.getBytes())){
                JWT jwt = JWTUtil.parseToken(token);
                // 验证token是否过期
                long exp = (long) jwt.getPayload("exp");
                if(exp < System.currentTimeMillis()) {
                    result.put("code", 401);
                    result.put("msg", "登录已过期，请重新登录");
                }else{
                    // 如果没有过期，放行
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }else{
                result.put("code", 401);
                result.put("msg", "请先登录");
            }
            if(!result.isEmpty()){
                servletResponse.setContentType("application/json;charset=utf-8");
                servletResponse.getWriter().write(new Gson().toJson(result));
            }
        }
    }

    @Override
    public void destroy() {
    }
}
