package cn.jasonone.servlet;

import cn.hutool.jwt.JWTUtil;
import cn.jasonone.bean.Admin;
import cn.jasonone.bean.Seat;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.AdminService;
import cn.jasonone.service.SelectSeatService;
import cn.jasonone.service.impl.AdminServiceImpl;
import cn.jasonone.service.impl.SelectSeatImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    private SelectSeatService seatService = new SelectSeatImpl();
    private void getInfo(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException  {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        List<Seat> info = seatService.getInfo(Integer.parseInt(body));
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", info);
        resp.getWriter().write(gson.toJson(result));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getInfo((BodyHttpServletRequestWrapper) req, resp);
    }
}
