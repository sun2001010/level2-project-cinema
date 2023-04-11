package cn.jasonone.mapper;

import cn.jasonone.bean.Seats;

/**
* @author Administrator
* @description 针对表【seats】的数据库操作Mapper
* @createDate 2023-04-11 09:34:53
* @Entity cn.jasonone.bean.Seats
*/
public interface SeatsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Seats record);

    int insertSelective(Seats record);

    Seats selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Seats record);

    int updateByPrimaryKey(Seats record);

}
