package cn.jasonone.servlet;

import cn.jasonone.bean.Seat;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.SelectSeatService;
import cn.jasonone.service.impl.SelectSeatImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/test")
public class TestServlet  extends HttpServlet {
    private SelectSeatService seatService = new SelectSeatImpl();
    private void getInfo(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
//        Seat seat = gson.fromJson(body,Seat.class);
//        seat.setHID(1);
        List<Seat> info = seatService.getInfo(Integer.parseInt(body));
        resp.getWriter().write(gson.toJson(info));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getInfo((BodyHttpServletRequestWrapper) req, resp);
    }
}
