package cn.jasonone.service.impl;

import cn.jasonone.bean.Score;
import cn.jasonone.mapper.CollectMapper;
import cn.jasonone.mapper.ScoreMapper;
import cn.jasonone.service.ScoreService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

public class ScoreServiceImpl implements ScoreService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public int selectAvg(String fName) {
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        return mapper.selectAvg(fName);
    }

    @Override
    public void addScore(Score record) {
        ScoreMapper mapper = sqlSession.getMapper(ScoreMapper.class);
        mapper.addScore(record);
    }
}
