package cn.jasonone.service;

import cn.jasonone.bean.FilmPlayer;
import cn.jasonone.bean.Films;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

public interface FilmPlayerService {
    void setSqlSession(SqlSession session);

    PageInfo<FilmPlayer> FilmPlayerSelect(FilmPlayer filmPlayer, int pageInfo, int pageNum);
}
