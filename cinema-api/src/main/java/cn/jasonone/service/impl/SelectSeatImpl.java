package cn.jasonone.service.impl;

import cn.jasonone.bean.Admin;
import cn.jasonone.bean.Seat;
import cn.jasonone.mapper.AdminMapper;
import cn.jasonone.mapper.SelectSeatMapper;
import cn.jasonone.service.SelectSeatService;
import lombok.Setter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SelectSeatImpl implements SelectSeatService {
    @Setter
    private SqlSession sqlSession;
    @Override
    public List<Seat> getInfo(int hid) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                SelectSeatMapper mapper = session.getMapper(SelectSeatMapper.class);
                return mapper.getSeatInfo(hid);
            }
        }

    }

    @Override
    public int getSeatStatus(Seat seat) throws IOException {
        try (InputStream is = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                SelectSeatMapper mapper = session.getMapper(SelectSeatMapper.class);
                int seatStatus = mapper.getSeatStatus(seat);
                return  seatStatus;
            }
        }
    }

}
