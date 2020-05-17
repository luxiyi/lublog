package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.UserMapper;
import com.lublog.po.LoginUser;
import com.lublog.po.User;
import com.lublog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Description: java类作用描述UserProvider
 * @Author: lxy
 * @time: 2020/4/7 0:35
 */
@SuppressWarnings("all")
@Component
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginUser findLoginByluser(LoginUser loginuser) {
        return userMapper.findLoginByluser(loginuser);
    }

    @Override
    public void updateData(LoginUser loginuser) {
        userMapper.updateData(loginuser);
    }

    @Override
    public void insertUser(String luser) {
        userMapper.insertUser(luser);
    }

    @Override
    public User findUserByluser(String userinfo) {
        return userMapper.findUserByluser(userinfo);
    }

    @Override
    public void updateUser(User userinfo) {
        userMapper.updateUser(userinfo);
    }

    @Override
    public void updataUsericon(String usericon, String luser) {
        userMapper.updataUsericon(usericon, luser);
    }

    @Override
    public LoginUser testUser(String luser) {
        return userMapper.testuser(luser);
    }

}
