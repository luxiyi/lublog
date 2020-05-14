package com.lublog.provider.dao;

import com.lublog.po.LoginUser;
import com.lublog.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description: UserMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:11
 */
public interface UserMapper {
    //登录
    @Select("select * from loginuser where luser=#{luser} and pass=#{pass}")
    LoginUser findLoginByluser(LoginUser loginuser);
    //注册账号密码
    @Insert("insert into loginuser (luser,pass,confirm) values (#{luser},#{pass},#{confirm})")
    void updateData(LoginUser loginuser);
    //同时插入账号到个人信息中
    @Insert("insert into bookuser (luser) values (#{luser})")
    void insertUser(String luser);
    //展示个人信息
    @Select("select * from vi_user where luser=#{param1}")
    User findUserByluser(String userinfo);
    //修改个人信息
    @Update("update bookuser set uname=#{uname},phone=#{phone},age=#{age},sex=#{sex},addr=#{addr} where luser=#{luser}")
    void updateUser(User userinfo);
    //修改个人头像
    @Update("update bookuser set usericon=#{param1} where luser=#{param2}")
    void updataUsericon(String usericon, String luser);
    @Select("select * from loginuser where luser=#{param1}")
    LoginUser testuser(String luser);
}
