package cn.jasonone.mapper;

import cn.jasonone.bean.Score;

/**
* @author Administrator
* @description 针对表【score(评分表)】的数据库操作Mapper
* @createDate 2023-04-11 09:34:39
* @Entity cn.jasonone.bean.Score
*/
public interface ScoreMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

}
