package com.lublog.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.lublog.constant.SysConstant;
import com.lublog.po.Profile;
import com.lublog.service.BlogService;
import com.lublog.service.ProfileService;
import com.lublog.utils.BlogStringUtils;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description: java类作用描述BlogController
 * @Author: lxy
 * @time: 2020/7/24 23:20
 */
@RestController
@Slf4j
public class AboutMeController {
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/showProfile", method = RequestMethod.GET)
    public Profile showProfile() {
        String title = SysConstant.PROFILE_SPACE;
        Profile profile = profileService.queryProfileByTitle(title);
        log.info("showProfile profile is {}", JSON.toJSONString(profile));
        return profile;
    }
}
