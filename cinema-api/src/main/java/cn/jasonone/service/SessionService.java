package cn.jasonone.service;

import cn.jasonone.bean.Films;
import cn.jasonone.bean.Session;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

public interface SessionService {
    void updateById(Session session);
    void deleteById(Integer fId);
    void setSqlSession(SqlSession session);
    PageInfo<Session> select(Session session, int pageInfo, int pageNum);
}
