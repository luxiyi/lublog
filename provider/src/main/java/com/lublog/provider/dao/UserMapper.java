package com.lublog.provider.dao;

import com.lublog.pojo.LoginUser;
import com.lublog.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description: UserMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:11
 */
public interface UserMapper {
    //测试
    @Select("select * from loginuser where luser=#{luser} and pass=#{pass} and flag=1")
    public LoginUser findRootByluser(LoginUser loginuser);
    //登录
    @Select("select * from loginuser where luser=#{luser} and pass=#{pass}")
    public LoginUser findLoginByluser(LoginUser loginuser);
    //注册账号密码
    @Insert("insert into loginuser (luser,pass,confirm) values (#{luser},#{pass},#{confirm})")
    public void updateData(LoginUser loginuser);
    //同时插入账号到个人信息中
    @Insert("insert into bookuser (luser) values (#{luser})")
    public void insertUser(String luser);
    //展示个人信息
    @Select("select * from vi_user where luser=#{param1}")
    public User findUserByluser(String userinfo);
    //修改个人信息
    @Update("update bookuser set uname=#{uname},phone=#{phone},age=#{age},sex=#{sex},addr=#{addr} where luser=#{luser}")
    public void updateUser(User userinfo);
    //修改个人头像
    @Update("update bookuser set usericon=#{param1} where luser=#{param2}")
    public void updataUsericon(String usericon, String luser);
    @Select("select * from loginuser where luser=#{param1}")
    public LoginUser testuser(String luser);
}
