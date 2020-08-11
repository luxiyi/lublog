package com.lublog.service;

import com.lublog.po.User;

/**
 * @Description: UserServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 22:09
 */

public interface UserService {
    User queryUserLogin(String userName, String password);

    void insertUser(String userName, String password, String luser);

    User findUserByluser(String userinfo);

    void updateUser(User userinfo);

    void updataUsericon(String usericon, String luser);

    User queryUserByUserName(String luser);
}
