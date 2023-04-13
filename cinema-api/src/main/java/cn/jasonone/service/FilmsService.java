package cn.jasonone.service;

import cn.jasonone.bean.Films;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * 影片信息服务接口
 * @author 青木
 */
public interface FilmsService {
    void insert(Films films);

    void deleteById(Integer fId);

    void selectById(Integer fId);

    void updateById(Films films);

    void setSqlSession(SqlSession session);
}
