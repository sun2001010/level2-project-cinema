package cn.jasonone.mapper;

import cn.jasonone.bean.Hall;

/**
* @author Administrator
* @description 针对表【hall(影厅表)】的数据库操作Mapper
* @createDate 2023-04-11 09:33:51
* @Entity cn.jasonone.bean.Hall
*/
public interface HallMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Hall record);

    int insertSelective(Hall record);

    Hall selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Hall record);

    int updateByPrimaryKey(Hall record);

}
