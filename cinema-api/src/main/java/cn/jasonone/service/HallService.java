package cn.jasonone.service;

import cn.jasonone.bean.Hall;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

public interface HallService {
    PageInfo<Hall> HallSelect(Hall record, int pageNum, int pageSize);
    void setSqlSession(SqlSession session);
}
