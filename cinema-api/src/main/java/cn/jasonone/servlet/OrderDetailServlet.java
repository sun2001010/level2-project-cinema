package cn.jasonone.servlet;

import cn.hutool.jwt.JWTUtil;
import cn.jasonone.bean.Admin;
import cn.jasonone.bean.Order;
import cn.jasonone.bean.OrderDetail;
import cn.jasonone.bean.Seat;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.AdminService;
import cn.jasonone.service.OrderService;
import cn.jasonone.service.SelectSeatService;
import cn.jasonone.service.impl.AdminServiceImpl;
import cn.jasonone.service.impl.OrderServiceImpl;
import cn.jasonone.service.impl.SelectSeatImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/orderdetail")
public class OrderDetailServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();
    private void buySeat(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        String newbody = body.replaceAll("\\[\"", "");
        String[] all = newbody.split("[^a-zA-Z0-9]+");
        String hid=all[all.length-1];
        String[] newArr = new String[all.length - 1];
        System.arraycopy(all, 0, newArr, 0, all.length - 1);
        Integer lastOrderId = orderService.getLastOrderId();
        for (String s : newArr) {
            String row = s.substring(0, 1);
            String col = s.substring(1);
            Integer seatId = orderService.getSeatId(row, Integer.parseInt(col), Integer.parseInt(hid));
            orderService.buySeat(seatId);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOId(lastOrderId);
            orderDetail.setRow(row);
            orderDetail.setCol(Integer.valueOf(col));
            orderService.orderSeat(orderDetail);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "下单成功");
        resp.getWriter().write(gson.toJson(result));
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession=(SqlSession) req.getAttribute("sqlSession");
        orderService.setSqlSession(sqlSession);
        try {
            buySeat((BodyHttpServletRequestWrapper) req,resp);

            sqlSession.commit();
        } catch (IOException e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }
}
