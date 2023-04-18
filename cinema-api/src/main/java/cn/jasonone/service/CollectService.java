package cn.jasonone.service;

import cn.jasonone.bean.Collect;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;


public interface CollectService {
    void setSqlSession(SqlSession session);

    PageInfo<Collect> CollectSelect(Collect collect, int pageInfo, int pageNum);

    void addCollect(Collect collect);

    int findNum(String fName);
}
