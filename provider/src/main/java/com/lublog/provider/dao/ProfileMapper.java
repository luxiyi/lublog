package com.lublog.provider.dao;

import com.lublog.po.BlogContent;
import com.lublog.po.Profile;
import com.lublog.vo.BlogShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Description: ProfileMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/8/16 21:43
 */
public interface ProfileMapper {
    //增加
    @Insert("insert into t_profile (profile_title,profile_content,profile_cover,profile_introduce) values (#{param1},#{param2},#{param3},#{param4})")
    void insertProfile(String profileTitle,String profileContent,String profileCover,String profileIntroduce);

    //根据标题查找
    @Select("select * from t_profile where profile_title=#{param1} and profile_flag = 0")
    Profile queryProfileByTitle(String profileTitle);

    //修改信息
    @Update("update t_profile set profile_content=#{param1},profile_cover=#{param2},profile_introduce=#{param3} where profile_title=#{param4} and profile_flag = 0")
    void updateProfileByTitle(String profileContent,String profileCover,String profileIntroduce,String profileTitle);
}
