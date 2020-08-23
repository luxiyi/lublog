package com.lublog.gateway.utils;

import com.lublog.po.BlogContent;
import com.lublog.po.Profile;

import java.util.Date;

/**
 * @Description: java类作用描述getBlogContent
 * @Author: lxy
 * @time: 2020/8/16 18:49
 */
public class getObjectUtils {


    public static Profile geyProfile(String profileContent, String profileCover, String profileIntroduce,
                                     String profileTitle, Profile profile){
        profile.setProfileContent(profileContent);
        profile.setProfileCover(profileCover);
        profile.setProfileTitle(profileTitle);
        profile.setProfileIntroduce(profileIntroduce);
        return profile;
    }
}
