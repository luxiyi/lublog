package com.lublog.provider.dao;

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
    @Select("select * from t_user where user_name=#{param1} and password=#{param2}")
    User findLoginByluser(String userName, String password);

    //同时插入账号到个人信息中
    @Insert("insert into t_user (user_name, password, user_phone) values (#{param1},#{param2},#{param3})")
    void insertUser(String userName, String password, String userPhone);

    //展示个人信息
    @Select("select * from vi_user where luser=#{param1}")
    User findUserByluser(String userinfo);

    //修改个人信息
    @Update("update bookuser set uname=#{uname},phone=#{phone},age=#{age},sex=#{sex},addr=#{addr} where luser=#{luser}")
    void updateUser(User userinfo);

    //修改个人头像
    @Update("update bookuser set usericon=#{param1} where luser=#{param2}")
    void updataUsericon(String usericon, String luser);

    @Select("select * from t_user where user_name=#{param1}")
    User queryUserByUserName(String userName);
}
