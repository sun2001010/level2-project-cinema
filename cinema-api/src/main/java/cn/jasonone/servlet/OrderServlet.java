package cn.jasonone.servlet;

import cn.hutool.json.JSON;
import cn.hutool.jwt.JWTUtil;
import cn.jasonone.bean.*;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.mapper.FilmsMapper;
import cn.jasonone.mapper.OrderMapper;
import cn.jasonone.service.AdminService;
import cn.jasonone.service.OrderService;
import cn.jasonone.service.SelectSeatService;
import cn.jasonone.service.impl.AdminServiceImpl;
import cn.jasonone.service.impl.OrderServiceImpl;
import cn.jasonone.service.impl.SelectSeatImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/order/*")
public class OrderServlet extends HttpServlet {
    private OrderService orderService = new OrderServiceImpl();

    private void getOderInfo(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        OrderSelect orderSelect = gson.fromJson(body, OrderSelect.class);
        Integer id = orderSelect.getId();
        Integer page = orderSelect.getPage();
        Integer limit = orderSelect.getLimit();
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                PageHelper.startPage(page,limit);
                List<Order> orderInfo = mapper.getOrderInfo(id);
                PageInfo<Order> orderPageInfo = new PageInfo<>(orderInfo);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "查询成功");
                result.put("data", orderPageInfo);
                resp.getWriter().write(gson.toJson(result));
            }
        }

    }
    private void getFilmByName(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        System.out.println(body);
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                Films filmByName = mapper.getFilmByName(body);
                System.out.println(filmByName);
                Map<String, Object> result = new HashMap<>();
                result.put("code", 200);
                result.put("msg", "查询成功");
                result.put("data", filmByName);
                resp.getWriter().write(gson.toJson(result));
            }
        }

    }

    private void buy(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        Order order = gson.fromJson(body, Order.class);
        Map<String, Object> result = new HashMap<>();
        orderService.buy(order);
        result.put("code", 200);
        result.put("msg", "下单成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void cancelOrder(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        Map<String, Object> result = new HashMap<>();
        orderService.cancelOrder(Integer.valueOf(body));
        result.put("code", 200);
        result.put("msg", "取消成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void deleteOrder(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 是否显示值为null的字段
                .serializeNulls()
                // 是否格式化json
                .setPrettyPrinting()
                .create();
        String body = req.getBody();
        Map<String, Object> result = new HashMap<>();
        orderService.deleteOrder(Integer.valueOf(body));
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/order/info":
                    getOderInfo((BodyHttpServletRequestWrapper) req,resp);
                    break;
                case "/order/cancel":
                    cancelOrder((BodyHttpServletRequestWrapper) req,resp);
                    break;
                case "/order/delete":
                    deleteOrder((BodyHttpServletRequestWrapper) req,resp);
                    break;
                case "/order/film":
                    getFilmByName((BodyHttpServletRequestWrapper) req,resp);
                    break;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession=(SqlSession) req.getAttribute("sqlSession");
        orderService.setSqlSession(sqlSession);
        try {
            buy((BodyHttpServletRequestWrapper) req,resp);

            sqlSession.commit();
        } catch (IOException e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        SqlSession sqlSession=(SqlSession) req.getAttribute("sqlSession");
//        orderService.setSqlSession(sqlSession);
//        try {
//            getOderInfo((BodyHttpServletRequestWrapper) req,resp);
//
//            sqlSession.commit();
//        } catch (IOException e) {
//            sqlSession.rollback();
//            throw new RuntimeException(e);
//        }
//    }
}
