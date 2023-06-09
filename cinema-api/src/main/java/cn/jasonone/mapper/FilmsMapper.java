package cn.jasonone.mapper;

import cn.jasonone.bean.Films;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【films(影片表)】的数据库操作Mapper
* @createDate 2023-04-11 09:33:29
* @Entity cn.jasonone.bean.Films
*/
public interface FilmsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Films record);

    int insertSelective(Films record);

    List<Films> findAll(Films films);

    Films selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(Films record);

    int updateByPrimaryKey(Films record);

}
