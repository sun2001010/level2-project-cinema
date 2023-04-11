package cn.jasonone.mapper;

import cn.jasonone.bean.Collect;

/**
* @author Administrator
* @description 针对表【collection(收藏表)】的数据库操作Mapper
* @createDate 2023-04-11 09:31:00
* @Entity cn.jasonone.bean.Collect
*/
public interface CollectionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

}
