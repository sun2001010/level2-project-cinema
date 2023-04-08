package cn.jasonone.service;

import cn.jasonone.bean.Admin;
import org.apache.ibatis.session.SqlSession;

/**
 * 用户信息服务接口
 * @author xujie
 */
public interface AdminService {
    /**
     * 用户注册
     * @param admin  用户信息
     */
    void register(Admin admin);

    void setSqlSession(SqlSession session);

    /**
     * 用户登录
     * @param admin
     * @return
     */
    Admin login(Admin admin);
}
