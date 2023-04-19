package cn.jasonone.mapper;

import cn.jasonone.bean.Users;

/**
* @author Administrator
* @description 针对表【users(用户表)】的数据库操作Mapper
* @createDate 2023-04-11 09:35:23
* @Entity cn.jasonone.bean.Users
*/
public interface UsersMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

}
