package cn.jasonone.service;

import cn.jasonone.bean.Films;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * 影片信息服务接口
 * @author 青木
 */
public interface FilmsService {
    void insert(Films films);

    void deleteById(Integer fId);

    Films selectById(Integer fId);

    void updateById(Films films);

    PageInfo<Films> select(Films films, int pageInfo, int pageNum);

    void setSqlSession(SqlSession session);
}
