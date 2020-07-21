package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.service.CommentService;
import com.lublog.vo.BlogShow;
import com.lublog.vo.CommentShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
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


    @RequestMapping("/showLastArticle")
    public Map<String, Object> showLastArticle(HttpSession session, BlogShow findBlogContent, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        int index = 0;
        int count = 3;
        List<BlogShow> blogContents = blogService.showLastArticle(index, count);
        BlogShow blogContent = blogService.findAllById(findBlogContent);
        log.info("totalPage = {}, page = {}, blogContents = {}", blogContents.toString());
        // 将所有书放入session
        session.setAttribute("blogContents", blogContents);
        session.setAttribute("blogContent", blogContent);
        session.setAttribute("bbid", bbid);
        result.put("blogContents", blogContents);
        return result;
    }

    @RequestMapping("/showLastComment")
    public Map<String, Object> showLastComment() {
        Map<String, Object> result = new HashMap<>();
        int index = 0;
        int count = 3;
        List<CommentShow> commentShows = commentService.showLastComment(index, count);
        log.info("totalPage = {}, page = {}, blogContents = {}", commentShows.toString());
        result.put("commentShows", commentShows);
        // 将所有书放入session
        return result;
    }
}

