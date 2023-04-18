package cn.jasonone.service.impl;

import cn.jasonone.bean.Films;
import cn.jasonone.bean.Session;
import cn.jasonone.mapper.FilmsMapper;
import cn.jasonone.mapper.SessionMapper;
import cn.jasonone.service.SessionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SessionServiceImpl implements SessionService {
    @Setter
    private SqlSession sqlSession;
    @Override
    public void updateById(Session session) {
        sqlSession.getMapper(SessionMapper.class).updateByPrimaryKey(session);
    }

    @Override
    public void deleteById(Integer fId) {
        sqlSession.getMapper(SessionMapper.class).deleteByPrimaryKey(Long.valueOf(fId));
    }

    @Override
    public PageInfo<Session> select(Session session, int pageInfo, int pageNum) {
        List sessionList = sqlSession.getMapper(SessionMapper.class).findAll(session);
        PageHelper.startPage(pageInfo, pageNum);
        return new PageInfo<Session>(sessionList);
    }
}
