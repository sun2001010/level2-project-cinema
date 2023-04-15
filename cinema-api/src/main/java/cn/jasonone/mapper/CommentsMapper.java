package cn.jasonone.mapper;

import cn.jasonone.bean.Comments;

import java.util.List;

/**
* @author 凌久渊
* @description 针对表【comments(评论表)】的数据库操作Mapper
* @createDate 2023-04-15 13:48:06
* @Entity cn.jasonone.bean.Comments
*/
public interface CommentsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);

    List<Comments> findAll(Comments comments);


}
