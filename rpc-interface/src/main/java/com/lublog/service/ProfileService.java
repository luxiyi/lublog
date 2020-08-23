package com.lublog.service;

import com.lublog.po.Profile;

/**
 * @Description: ProfileServicejava类作用描述
 * @Author: lxy
 * @time: 2020/8/16 22:03
 */
public interface ProfileService {
    void insertProfile(String profileTitle,String profileContent,String profileCover,String profileIntroduce);

    //根据标题查找
    Profile queryProfileByTitle(String profileTitle);

    //修改信息
    void updateProfileByTitle(String profileContent,String profileCover,String profileIntroduce,String profileTitle);
}
