package cn.jasonone.mapper;

import cn.jasonone.bean.Cinema;

import java.util.List;

/**
* @author Administrator
* @description 针对表【cinema(影院表)】的数据库操作Mapper
* @createDate 2023-04-09 10:48:24
* @Entity cn.jasonone.bean.Cinema
*/
public interface CinemaMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Long id);

    List<Cinema> findAll(Cinema cinema);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);

}
