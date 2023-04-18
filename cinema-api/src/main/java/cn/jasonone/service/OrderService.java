package cn.jasonone.service;

import cn.jasonone.bean.Order;
import cn.jasonone.bean.OrderDetail;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public interface OrderService  {
//    List<Order> getOrderInfo(int uid) throws IOException;
    void buy(Order order) throws IOException;
    void buySeat(Integer sid) throws IOException;
    void setSqlSession(SqlSession session);
    Integer getSeatId(String row,Integer col,Integer hid) throws IOException;
    Integer getLastOrderId() throws IOException;
    void orderSeat(OrderDetail orderDetail) throws IOException;
    void cancelOrder(Integer oid) throws IOException;
    void deleteOrder(Integer oid) throws IOException;
}
