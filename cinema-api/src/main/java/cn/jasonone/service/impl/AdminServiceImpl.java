package cn.jasonone.service.impl;

import cn.hutool.crypto.digest.MD5;
import cn.jasonone.bean.Admin;
import cn.jasonone.mapper.AdminMapper;
import cn.jasonone.service.AdminService;
import cn.hutool.core.util.RandomUtil;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

public class AdminServiceImpl implements AdminService {
    @Setter
    private SqlSession sqlSession;

    @Override
    public Admin login(Admin admin) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin user = mapper.findByAName(admin);

        return user;
    }
}
