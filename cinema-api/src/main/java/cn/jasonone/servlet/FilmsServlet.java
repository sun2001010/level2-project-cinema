package cn.jasonone.servlet;

import cn.jasonone.bean.Films;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.FilmsService;
import cn.jasonone.service.impl.FilmsServiceImpl;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/films/*")
public class FilmsServlet extends HttpServlet {
    private FilmsService filmsService = new FilmsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        filmsService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/films/selectById":
                    selectById((BodyHttpServletRequestWrapper) req, resp);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        filmsService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/films/deleteById":
                    deleteByID((BodyHttpServletRequestWrapper) req, resp);
                    break;
                default:
                    super.doDelete(req,resp);
            }
            sqlSession.commit();
        }catch (IOException e){
            sqlSession.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        filmsService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/films/select":
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        filmsService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/films/insert":
                    insert((BodyHttpServletRequestWrapper) req, resp);
                    break;
                case "/films/updateById":
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
    private void insert(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException{
        BodyHttpServletRequestWrapper bodyReq = req;
        Gson gson = new Gson();
        String body = bodyReq.getBody();
        Films films = gson.fromJson(body, Films.class);
        filmsService.insert(films);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "添加成功");
        resp.getWriter().write(gson.toJson(result));
    }

    private void deleteByID(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException{
        BodyHttpServletRequestWrapper bodyReq = req;
        Gson gson = new Gson();
        String body = bodyReq.getBody();
        Films films = gson.fromJson(body, Films.class);
        filmsService.deleteById(films.getFId());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "删除成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void selectById(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException{
        Gson gson = new Gson();
        String body = req.getBody();
        Films films = gson.fromJson(body, Films.class);
        filmsService.selectById(films.getFId());
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void updateById(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException{
        Gson gson = new Gson();
        String body = req.getBody();
        Films films = gson.fromJson(body, Films.class);
        filmsService.updateById(films);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "修改成功");
        resp.getWriter().write(gson.toJson(result));
    }
    private void select(BodyHttpServletRequestWrapper req,HttpServletResponse resp)throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        Films films = gson.fromJson(body, Films.class);
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
        PageInfo<Films> filmsPageInfo = filmsService.select(films, pageNum, pageSize);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", filmsPageInfo);
        resp.getWriter().write(gson.toJson(result));
    }
}
