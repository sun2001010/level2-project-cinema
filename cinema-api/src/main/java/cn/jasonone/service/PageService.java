package cn.jasonone.service;

import cn.jasonone.bean.Films;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

public interface PageService {
    void setSqlSession(SqlSession session);

    PageInfo<Films> FilmSelect(Films films,int pageInfo,int pageNum);
}
