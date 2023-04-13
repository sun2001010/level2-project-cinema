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
    public void register(Admin admin) {
        // 生成盐
        String salt = RandomUtil.randomString(6);
        admin.setASalt(salt);
        admin.setAPassword(salt+admin.getAPassword()+salt);
        sqlSession.getMapper(AdminMapper.class).insert(admin);
    }

    @Override
    public Admin login(Admin admin) {
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin user = mapper.findByAName(admin.getAName());
//        System.out.println(user);
        if(user != null){
            // 获得盐
            String salt = user.getASalt();
            // 加盐加密
            String password = salt+admin.getAPassword()+salt;
            password = MD5.create().digestHex(password);
            // 验证密码
            if(password.equals(user.getAPassword())){
                user.setAPassword(null);
                user.setASalt(null);
                return user;
            }
        }
        return user;
    }
}
