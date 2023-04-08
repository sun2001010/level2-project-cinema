package cn.jasonone.filter;

import cn.hutool.core.io.IoUtil;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

public class BodyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static final String APPLICATION_JSON= "application/json";
    @Getter
    private String body;

    public BodyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        String contentType = request.getContentType();
        // 判断请求体是否是json格式
        if(contentType.contains(APPLICATION_JSON)){
            // 通过输入流,将请求体中的json数据读取出来
            this.body = IoUtil.read(request.getInputStream(), "UTF-8");
        }
    }
}
