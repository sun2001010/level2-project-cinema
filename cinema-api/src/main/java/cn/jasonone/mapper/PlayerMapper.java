package cn.jasonone.mapper;

import cn.jasonone.bean.Player;

/**
* @author Administrator
* @description 针对表【player(演员表)】的数据库操作Mapper
* @createDate 2023-04-11 09:34:24
* @Entity cn.jasonone.bean.Player
*/
public interface PlayerMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Player record);

    int insertSelective(Player record);

    Player selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Player record);

    int updateByPrimaryKey(Player record);

}
