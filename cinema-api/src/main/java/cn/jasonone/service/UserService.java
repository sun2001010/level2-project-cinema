package cn.jasonone.service;

import cn.jasonone.bean.Users;
import org.apache.ibatis.session.SqlSession;

public interface UserService {
    void register(Users users);

    Users login(Users users);

    void setSqlSession(SqlSession session);
}
