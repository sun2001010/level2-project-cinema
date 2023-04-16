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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/select/check")
public class SelectCheckServlet extends HttpServlet {
    private SelectSeatService seatService = new SelectSeatImpl();

    private void getStatus(BodyHttpServletRequestWrapper req, HttpServletResponse resp) throws IOException {

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
        boolean b=true;
        String hid=all[all.length-1];
        String[] newArr = new String[all.length - 1];
        System.arraycopy(all, 0, newArr, 0, all.length - 1);
        System.out.println(Arrays.toString(newArr));
        for (String s : newArr) {
            String row = s.substring(0, 1);
            String col = s.substring(1);
            Seat seat = new Seat();
            seat.setSRow(row);
            seat.setHId(Integer.valueOf(hid));
            System.out.println(row);
            System.out.println(col);
            System.out.println(1);
            seat.setSCol(Integer.valueOf(col));
            int seatStatus = seatService.getSeatStatus(seat);
            if (seatStatus == 1) {
               b=false;
            }
        }
        Map<String, Object> result = new HashMap<>();
        if (!b){
            result.put("code", 400);
            result.put("msg", "手慢了有人抢座!");
        }
        else {
            result.put("code", 200);
            result.put("msg", "购票成功");
        }
        resp.getWriter().write(gson.toJson(result));


//        seatService.getSeatStatus()
        //        List<Seat> info = seatService.getInfo(Integer.parseInt(body));
//        Map<String,Object> result = new HashMap<>();
//        result.put("code", 200);
//        result.put("msg", "查询成功");
//        result.put("data", info);
//        resp.getWriter().write(gson.toJson(result));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getStatus((BodyHttpServletRequestWrapper) req, resp);
    }
}
