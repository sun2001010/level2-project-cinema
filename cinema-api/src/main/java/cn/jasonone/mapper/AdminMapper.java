package cn.jasonone.mapper;

import cn.jasonone.bean.Admin;

/**
* @author Administrator
* @description 针对表【admin(管理员表)】的数据库操作Mapper
* @createDate 2023-04-10 09:25:31
* @Entity cn.jasonone.bean.Admin
*/
public interface AdminMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin findByAName(Admin admin);
}
