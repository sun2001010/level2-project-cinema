package cn.jasonone.servlet;

import cn.jasonone.bean.Admin;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.AdminService;
import cn.jasonone.service.impl.AdminServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cn.hutool.jwt.JWTUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private AdminService adminService = new AdminServiceImpl();
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        adminService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/admin/register":
                    register((BodyHttpServletRequestWrapper) req, resp);
                    break;
                default:
                   super.doPut(req, resp);
            }
            // 如果没有异常，提交事务
            sqlSession.commit();
        } catch (IOException e) {
            // 如果有异常，回滚事务
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        adminService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/admin/login":
                    login((BodyHttpServletRequestWrapper) req, resp);
                    break;
                default:
                    super.doPost(req, resp);
            }
            // 如果没有异常，提交事务
            sqlSession.commit();
        } catch (IOException e) {
            // 如果有异常，回滚事务
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }

    private void login(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException  {
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        Admin admin = gson.fromJson(body, Admin.class);
        admin = adminService.login(admin);
        Map<String,Object> result = new HashMap<>();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(admin == null){
            result.put("code", 400);
            result.put("msg", "用户名或密码错误");
        }else{
            // 创建token
            Map<String,Object> token = new HashMap<>();
            token.put("username", admin.getAName());
            token.put("id", admin.getAId());
            token.put("exp", System.currentTimeMillis()+1000*60*30);
            String jwtPassword = req.getServletContext().getInitParameter("jwt_password");
            String token1 = JWTUtil.createToken(token, jwtPassword.getBytes());

            result.put("code", 200);
            result.put("msg", "登录成功");
            result.put("data", admin);
            result.put("token", token1);
        }
        resp.getWriter().write(gson.toJson(result));

    }

    private void register(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        Gson gson = new Gson();
        String body = req.getBody();
        Admin admin = gson.fromJson(body, Admin.class);

        adminService.register(admin);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "注册成功");
        resp.getWriter().write(gson.toJson(result));
    }
}
