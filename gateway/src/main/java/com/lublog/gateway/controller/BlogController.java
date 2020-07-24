package com.lublog.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.lublog.service.BlogService;
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
@Controller
@Slf4j
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value ="blog", method = RequestMethod.GET)
    public String findBlog(@RequestParam("blogId")Integer blogId, HttpSession session) {
        BlogShow blogShow = blogService.findBookById(blogId);
        log.info("blogShow is {}", JSON.toJSONString(blogShow));
        session.setAttribute("blogShow", blogShow);
        return "front/blog";
    }

    @RequestMapping(value = "showBlog", method = RequestMethod.POST)
    @ResponseBody
    public BlogShow showBlog(@RequestParam("blogId")Integer blogId) {
        BlogShow blogShow = blogService.findBookById(blogId);
        log.info("blogShow is {}", JSON.toJSONString(blogShow));
        return blogShow;
    }
}
