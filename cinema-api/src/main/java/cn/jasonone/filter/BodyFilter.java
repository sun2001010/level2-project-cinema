package cn.jasonone.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 请求体处理过滤器
 * <pre>
 *     1. 当请求体中的数据是json格式时, 将请求体中的json数据读取出来, 并将其封装到{@link BodyHttpServletRequestWrapper request}对象中
 *     2. 如果需要获取请求体中的json数据, 可以通过{@link BodyHttpServletRequestWrapper#getBody()}方法获取
 * </pre>
 */
@WebFilter("/*")
public class BodyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new BodyHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }

    @Override
    public void destroy() {
    }
}
