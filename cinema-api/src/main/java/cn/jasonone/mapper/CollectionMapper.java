package cn.jasonone.mapper;

import cn.jasonone.bean.Collection;

/**
* @author Administrator
* @description 针对表【collection(收藏表)】的数据库操作Mapper
* @createDate 2023-04-11 09:31:00
* @Entity cn.jasonone.bean.Collection
*/
public interface CollectionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

}
