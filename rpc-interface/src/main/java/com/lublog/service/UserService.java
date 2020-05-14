package com.lublog.service;

import com.lublog.po.LoginUser;
import com.lublog.po.User;

/**
 * @Description: UserServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 22:09
 */

public interface UserService {
    LoginUser findLoginByluser(LoginUser loginuser);
    void updateData(LoginUser loginuser);
    void insertUser(String luser);
    User findUserByluser(String userinfo);
    void updateUser(User userinfo);
    void updataUsericon(String usericon, String luser);
    LoginUser testUser(String luser);
}
