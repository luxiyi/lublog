package com.lublog.provider.serviceimpl;

import com.lublog.po.Profile;
import com.lublog.provider.dao.ProfileMapper;
import com.lublog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述ProfileServiceImpl
 * @Author: lxy
 * @time: 2020/8/16 22:04
 */
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public void insertProfile(String profileTitle,String profileContent,String profileCover,String profileIntroduce) {
        profileMapper.insertProfile(profileTitle,profileContent,profileCover,profileIntroduce);
    }

    @Override
    public Profile queryProfileByTitle(String profileTitle) {
        return profileMapper.queryProfileByTitle(profileTitle);
    }

    @Override
    public void updateProfileByTitle(String profileContent, String profileCover, String profileIntroduce, String profileTitle) {
        profileMapper.updateProfileByTitle(profileContent,profileCover,profileIntroduce,profileTitle);
    }
}
