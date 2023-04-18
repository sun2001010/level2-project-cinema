package cn.jasonone.mapper;

import cn.jasonone.bean.Films;
import cn.jasonone.bean.Session;

import java.util.List;

/**
* @author Administrator
* @description 针对表【session(场次表)】的数据库操作Mapper
* @createDate 2023-04-11 09:35:10
* @Entity cn.jasonone.bean.Session
*/
public interface SessionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Session record);

    int insertSelective(Session record);

    Session selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Session record);

    int updateByPrimaryKey(Session record);

    List<Session> findAll(Session session);
}
