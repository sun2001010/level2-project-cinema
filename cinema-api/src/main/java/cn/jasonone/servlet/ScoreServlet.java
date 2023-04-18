package cn.jasonone.servlet;

import cn.jasonone.bean.Collect;
import cn.jasonone.bean.Score;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.ScoreService;
import cn.jasonone.service.impl.ScoreServiceImpl;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScoreServlet extends HttpServlet {

    private ScoreService scoreService = new ScoreServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        scoreService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/score/selectAvg":
                    avg((BodyHttpServletRequestWrapper) req, resp);
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
        scoreService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            //去除contentPath
            switch (requestURI) {
                case "/score/add":
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
    private void avg(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        int avg = scoreService.selectAvg(body);

        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "查询成功");
        result.put("data", avg);
        resp.getWriter().write(gson.toJson(result));
    }
    private void add(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        Score score = gson.fromJson(body, Score.class);

        scoreService.addScore(score);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "评分成功");
        resp.getWriter().write(gson.toJson(result));
    }
}
