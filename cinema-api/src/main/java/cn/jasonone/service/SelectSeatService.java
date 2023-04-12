package cn.jasonone.service;

import cn.jasonone.bean.Seat;

import java.io.IOException;
import java.util.List;

public interface SelectSeatService {
    List<Seat> getInfo(int hid) throws IOException;
    int getSeatStatus(Seat seat) throws IOException;
}
