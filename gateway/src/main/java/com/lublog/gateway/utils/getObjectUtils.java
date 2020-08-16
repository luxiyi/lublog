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
    public static BlogContent geyBlogContent(BlogContent blogContent, String categoryIdStr, String tagIdStr, String title, String content, String author,
                                                String introduce, String blogCover){
        int categoryId = Integer.parseInt(categoryIdStr);
        int tagId = Integer.parseInt(tagIdStr);
        blogContent.setTitle(title);
        blogContent.setContent(content);
        blogContent.setPubdate(new Date());
        blogContent.setCategoryid(categoryId);
        blogContent.setTagid(tagId);
        blogContent.setAuthor(author);
        blogContent.setIntroduce(introduce);
        blogContent.setBlogcover(blogCover);
        return blogContent;
    }

    public static Profile geyProfile(String profileContent, String profileCover, String profileIntroduce,
                                     String profileTitle, Profile profile){
        profile.setProfileContent(profileContent);
        profile.setProfileCover(profileCover);
        profile.setProfileTitle(profileTitle);
        profile.setProfileIntroduce(profileIntroduce);
        return profile;
    }
}
