package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.UserMapper;
import com.lublog.po.User;
import com.lublog.service.UserService;
import lombok.extern.slf4j.Slf4j;
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
    public User queryUserLogin(String userName, String password) {
        return userMapper.findLoginByluser(userName, password);
    }

    @Override
    public void insertUser(String userName, String password, String userPhone) {
        userMapper.insertUser(userName, password, userPhone);
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
    public User queryUserByUserName(String userName) {
        return userMapper.queryUserByUserName(userName);
    }

    @Override
    public User queryUserByUserPhone(String userContact) {
        return userMapper.queryUserByUserPhone(userContact);
    }

    @Override
    public User queryUserByUserEmail(String userContact) {
        return userMapper.queryUserByUserEmail(userContact);
    }

}
