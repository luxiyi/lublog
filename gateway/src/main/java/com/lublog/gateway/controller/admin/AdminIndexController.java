package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.lublog.po.Comment;
import com.lublog.po.Statistics;
import com.lublog.service.BlogService;
import com.lublog.service.CommentService;
import com.lublog.service.StatisticsService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述BlogController
 * @Author: lxy
 * @time: 2020/7/16 2:08
 */

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminIndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;


    @RequestMapping("/showLastBlogs")
    public Map<String, Object> showLastBlogs() {
        Map<String, Object> result = new HashMap<>();
        int index = 0;
        int count = 3;
        List<BlogShow> blogContents = blogService.showLastBlogs(index, count);
        log.info("totalPage = {}, page = {}, blogContents = {}", blogContents.toString());
        result.put("blogContents", blogContents);
        return result;
    }

    @RequestMapping("/showLastComments")
    public Map<String, Object> showLastComments() {
        Map<String, Object> result = new HashMap<>();
        int index = 0;
        int count = 3;
        List<Comment> commentShows = commentService.findAllByIndex(index, count);
        log.info("totalPage = {}, page = {}, blogContents = {}", commentShows.toString());
        result.put("commentShows", commentShows);
        // 将所有书放入session
        return result;
    }
}

