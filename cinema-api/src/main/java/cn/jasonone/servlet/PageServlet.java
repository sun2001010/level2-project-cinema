package cn.jasonone.servlet;

import cn.jasonone.bean.Films;
import cn.jasonone.filter.BodyHttpServletRequestWrapper;
import cn.jasonone.service.PageService;
import cn.jasonone.service.impl.PageServiceImpl;
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

@WebServlet("/home/*")
public class PageServlet extends HttpServlet {
    private PageService pageService = new PageServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlSession sqlSession = (SqlSession) req.getAttribute("sqlSession");
        pageService.setSqlSession(sqlSession);
        try {
            String requestURI = req.getRequestURI();
            // 去除contextPath
            requestURI = requestURI.substring(req.getContextPath().length());
            switch (requestURI) {
                case "/home/select":
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

    private void select(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        String body = req.getBody();
        Films films=gson.fromJson(body,Films.class);
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
        PageInfo<Films>pageInfo=pageService.FilmSelect(films,pageNum,pageSize);
        Map<String,Object> result=new HashMap<>();
        result.put("code",200);
        result.put("msg","查询成功");
        result.put("data",pageInfo);
        resp.getWriter().write(gson.toJson(result));
    }

}