package cn.jasonone.mapper;

import cn.jasonone.bean.Seat;

import java.util.List;

public interface SelectSeatMapper {
    List<Seat> getSeatInfo(int hid);
    int getSeatStatus(Seat seat);
}
