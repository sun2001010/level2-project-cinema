package cn.jasonone.mapper;

import cn.jasonone.bean.FilmPlayer;

/**
* @author 凌久渊
* @description 针对表【film_player(电影演员关系表)】的数据库操作Mapper
* @createDate 2023-04-10 10:56:11
* @Entity cn.jasonone.bean.FilmPlayer
*/
public interface FilmPlayerMapper {

    int deleteByPrimaryKey(Long id);

    int insert(FilmPlayer record);

    int insertSelective(FilmPlayer record);

    FilmPlayer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FilmPlayer record);

    int updateByPrimaryKey(FilmPlayer record);

}
