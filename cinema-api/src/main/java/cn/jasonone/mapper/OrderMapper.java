package cn.jasonone.mapper;

import cn.jasonone.bean.Films;
import cn.jasonone.bean.Order;
import cn.jasonone.bean.OrderDetail;
import cn.jasonone.bean.Seat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【order(订单表)】的数据库操作Mapper
* @createDate 2023-04-11 09:34:08
* @Entity cn.jasonone.bean.Order
*/
public interface OrderMapper {

    List<Order> getOrderInfo(int uid);
    void buy(Order order);
   Films getFilmByName(String name);
    void orderSeat(OrderDetail orderDetail);
    void buySeat(int sid);
   Integer getSeatId(@Param("row")String row, @Param("col")Integer col,@Param("hid") Integer hid);
   Integer getLastOrderId();
   void cancelOrder(Integer oid);

   void deleteOrder(Integer oid);


}
