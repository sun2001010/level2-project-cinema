package cn.jasonone.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import cn.jasonone.bean.Admin;
import cn.jasonone.bean.Users;
import cn.jasonone.mapper.AdminMapper;
import cn.jasonone.mapper.UsersMapper;
import cn.jasonone.service.UserService;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    @Override
    public void register(Users user) {
        String salt = RandomUtil.randomString(6);
        user.setUSalt(salt);
//        user.setUPassword(salt+user.getUPassword()+salt);
        System.out.println(user);
        sqlSession.getMapper(UsersMapper.class).insert(user);
    }

    @Override
    public Users login(Users users) {
        UsersMapper mapper = sqlSession.getMapper(UsersMapper.class);
        System.out.println(users);
        Users user = mapper.findByUName(users);
        System.out.println(users.getUName());
        System.out.println(user);
//        if(user != null){
//            // 获得盐
//            String salt = user.getUSalt();
//            // 加盐加密
//            String password = salt+users.getUPassword()+salt;
//            password = MD5.create().digestHex(password);
//            // 验证密码
//            if(password.equals(user.getUPassword())){
//                user.setUPassword(null);
//                user.setUSalt(null);
//                return user;
//            }
//        }
        return user;
    }


    @Setter
    private SqlSession sqlSession;
}
