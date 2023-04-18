package cn.jasonone.service.impl;

import cn.jasonone.bean.Order;
import cn.jasonone.bean.OrderDetail;
import cn.jasonone.bean.Seat;
import cn.jasonone.mapper.OrderMapper;
import cn.jasonone.mapper.SelectSeatMapper;
import cn.jasonone.service.OrderService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderServiceImpl implements OrderService {
//    @Override
//    public List<Order> getOrderInfo(int uid) throws IOException {
//        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
//            try (SqlSession session = sqlSessionFactory.openSession()) {
//                OrderMapper mapper = session.getMapper(OrderMapper.class);
//                return mapper.getOrderInfo(uid);
//            }
//        }
//    }

    @Override
    public void buy(Order order) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                mapper.buy(order);
                session.commit();
            }
        }
    }

    @Override

    public void buySeat(Integer sid) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                mapper.buySeat(sid);
                session.commit();
            }
        }
    }
    public Integer getSeatId(String row,Integer col,Integer hid) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                session.commit();
                return mapper.getSeatId(row, col, hid);
            }
        }
    }
    public Integer getLastOrderId() throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                session.commit();
                return mapper.getLastOrderId();
            }
        }

    }
    public void orderSeat(OrderDetail orderDetail) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                mapper.orderSeat(orderDetail);
                session.commit();
            }
        }
    }
    public void cancelOrder(Integer oid) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                OrderMapper mapper = session.getMapper(OrderMapper.class);
                mapper.cancelOrder(oid);
                session.commit();
            }
        }
    }
   public void deleteOrder(Integer oid) throws IOException {
       try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
           SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
           try (SqlSession session = sqlSessionFactory.openSession()) {
               OrderMapper mapper = session.getMapper(OrderMapper.class);
               mapper.deleteOrder(oid);
               session.commit();
           }
       }
   }
    @Override
    public void setSqlSession(SqlSession session) {

    }
}
