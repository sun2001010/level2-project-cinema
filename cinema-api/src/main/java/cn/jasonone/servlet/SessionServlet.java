package cn.jasonone.servlet;

import cn.jasonone.bean.Films;
import cn.jasonone.bean.Session;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.SessionService;
import cn.jasonone.service.impl.SessionServiceImpl;
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

@WebServlet("/session/*")
public class SessionServlet extends HttpServlet {
    private SessionService sessionService = new SessionServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        sessionService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/session/select":
                    select((BodyHttpServletRequestWrapper) req, resp);
                    break;
                default:
                    super.doGet(req,resp);
            }
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        sessionService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/session/deleteById":
                    deleteByID((BodyHttpServletRequestWrapper) req, resp);
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
        sessionService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/session/updateById":
                    updateById((BodyHttpServletRequestWrapper) req, resp);
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
    private void updateById(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException{
        Gson gson = new Gson();
        String body = req.getBody();
        Session session = gson.fromJson(body, Session.class);
        sessionService.updateById(session);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void deleteByID(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException{
        BodyHttpServletRequestWrapper bodyReq = req;
        Gson gson = new Gson();
        String body = bodyReq.getBody();
        Session session = gson.fromJson(body, Session.class);
        sessionService.deleteById(session.getSeId());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void select(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        Session session = gson.fromJson(body, Session.class);
        String page=req.getParameter("page");
        String limit=req.getParameter("limit");
        int pageNum=1;
        int pageSize=10;
        if (page != null) {
            pageNum = Integer.parseInt(page);
        }
        if (limit != null) {
            pageSize = Integer.parseInt(limit);
        }
        PageInfo<Session> filmsPageInfo = sessionService.select(session, pageNum, pageSize);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", filmsPageInfo);
        resp.getWriter().write(gson.toJson(result));
    }
}
