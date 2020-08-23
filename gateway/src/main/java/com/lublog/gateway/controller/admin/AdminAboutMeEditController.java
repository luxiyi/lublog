package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lublog.constant.SysConstant;
import com.lublog.gateway.utils.getObjectUtils;
import com.lublog.po.Profile;
import com.lublog.service.ProfileService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @Description: java类作用描述AdminAboutMeEditController
 * @Author: lxy
 * @time: 2020/8/16 16:53
 */

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminAboutMeEditController {
    @Autowired
    private ProfileService profileService;

    /**
     * 保存发布文章
     */
    @RequestMapping(value = "/pushProfile", method = RequestMethod.POST)
    public String pushProfile(HttpServletRequest request, String profileContent, String profileCover, String profileIntroduce, String profileTitle) {
        String msg = "发布成功!";
        Profile profile = getObjectUtils.geyProfile(profileContent,profileCover,profileIntroduce,profileTitle,new Profile());
        if (profileService.queryProfileByTitle(profileTitle) != null) {
            log.info("修改介绍");
            try {
                profileService.updateProfileByTitle(profileContent,profileCover,profileIntroduce,profileTitle);
                msg = "更改成功";
                request.setAttribute("profile", profile);
                return msg;
            } catch (Exception e) {
                log.error("blog update failed is {}", e);
                msg = "更新失败，请联系帅气的管理员";
                return msg;
            }
        }

        try {
            profileService.insertProfile(profileTitle,profileContent,profileCover,profileIntroduce);
            request.setAttribute("profile", profile);
            return msg;
        } catch (Exception e) {
            log.error("blog push failed is {}", e);
            msg = "发布失败，请联系帅气的管理员";
            return msg;
        }
    }

    @RequestMapping(value = "/showProfile", method = RequestMethod.GET)
    public Profile showProfile() {
        String title = SysConstant.PROFILE_SPACE;
        Profile profile = profileService.queryProfileByTitle(title);
        log.info("admin showProfile profile is {}", JSON.toJSONString(profile));
        return profile;
    }

}
