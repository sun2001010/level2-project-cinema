package cn.jasonone.mapper;

import cn.jasonone.bean.Order;

/**
* @author Administrator
* @description 针对表【order(订单表)】的数据库操作Mapper
* @createDate 2023-04-11 09:34:08
* @Entity cn.jasonone.bean.Order
*/
public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

}
