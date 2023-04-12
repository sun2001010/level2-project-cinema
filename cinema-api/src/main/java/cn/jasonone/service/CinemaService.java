package cn.jasonone.service;

import com.github.pagehelper.PageInfo;
import cn.jasonone.bean.Cinema;
import org.apache.ibatis.session.SqlSession;

public interface CinemaService {
    //分类插件
    PageInfo<Cinema> CinemaSelect(Cinema record, int pageNum, int pageSize);
    void setSqlSession(SqlSession session);
}
