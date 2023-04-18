package cn.jasonone.service;

import cn.jasonone.bean.Seat;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public interface SelectSeatService {
    List<Seat> getInfo(int hid) throws IOException;
    int getSeatStatus(Seat seat) throws IOException;
    void setSqlSession(SqlSession session);
}
