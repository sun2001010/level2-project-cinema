package cn.jasonone.servlet;

import cn.jasonone.bean.Collect;
import cn.jasonone.bean.Films;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.CollectService;
import cn.jasonone.service.impl.CollectServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/collect/*")
public class CollectServlet extends HttpServlet {
    private CollectService collectService = new CollectServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        collectService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/collect/select":
                    select((BodyHttpServletRequestWrapper) req, resp);
                    break;

                default:
                    super.doGet(req, resp);
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
        collectService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/collect/findNum":
                    findNum((BodyHttpServletRequestWrapper) req, resp);
                    break;
                default:
                    super.doPost(req,resp);
            }
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        collectService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/collect/add":
                    add((BodyHttpServletRequestWrapper) req, resp);
                    break;

                default:
                    super.doPut(req,resp);
            }

            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }

    private void select(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        Collect collect=gson.fromJson(body, Collect.class);
        //开始使用分类插件
        String page=req.getParameter("page");
        String limit=req.getParameter("limit");
        int pageNum=1;
        int pageSize=10;
        if(page !=null){
            pageNum=Integer.parseInt(page);
        }
        if (limit !=null){
            pageSize=Integer.parseInt(limit);
        }
        PageInfo<Collect> pageInfo=collectService.CollectSelect(collect,pageNum,pageSize);
        Map<String,Object> result=new HashMap<>();
        result.put("code",200);
        result.put("msg","查询成功");
        result.put("data",pageInfo);
        resp.getWriter().write(gson.toJson(result));
    }
    private void add(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        Collect collect = gson.fromJson(body, Collect.class);

        collectService.addCollect(collect);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "收藏成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void findNum(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

        BodyHttpServletRequestWrapper bodyReq = req;
        Gson gson = new Gson();
        String body = bodyReq.getBody();
        int num = collectService.findNum(body);

        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", num);
        resp.getWriter().write(gson.toJson(result));
    }
}
