package cn.jasonone.service;

import cn.jasonone.bean.Score;
import org.apache.ibatis.session.SqlSession;

public interface ScoreService {
    void setSqlSession(SqlSession session);

    int selectAvg(String fName);

    void addScore(Score record);
}
