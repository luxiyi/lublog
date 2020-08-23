package com.lublog.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.lublog.constant.SysConstant;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.utils.BlogStringUtils;
import com.lublog.utils.IPUtils;
import com.lublog.utils.MapCache;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    protected MapCache cache = MapCache.getInstance();

    @GetMapping(value = "blog")
    public String findBlog(@RequestParam("blogId") String blogIdStr, HttpServletRequest request) {
        Integer blogId = BlogStringUtils.getNum(blogIdStr);
        BlogShow blogShow = blogService.findBlogById(blogId);
        log.info("blogShow is {}", JSON.toJSONString(blogShow));
        request.setAttribute("blogShow", blogShow);
//        log.info("checkHitsFrequency = {}", checkHitsFrequency(request, blogId));
        if (!checkHitsFrequency(request, blogId)) {
            // 更新文章点击量
            Integer views = blogShow.getViews();
            if (views == 0 || views == null) {
                views = 0;
            }
            views = views + 1;
            blogService.updateViewsById(views, blogId);
        }
        return "front/blog";
    }


    /**
     * 检查同一个ip在5分钟之内是否访问同一篇文章
     *
     * @param request
     * @param blogId
     * @return true——5分钟内访问过，false——5分钟内没有访问过
     */
    private boolean checkHitsFrequency(HttpServletRequest request, Integer blogId) {
        String val = IPUtils.getIpAddrByRequest(request) + ":" + blogId;
        Integer count = cache.hget(SysConstant.HITS_FREQUENCY, val);
        log.info("cache.hget = {}", count);
        if (null != count && count > 0) {
            return true;
        }
        cache.hset(SysConstant.HITS_FREQUENCY, val, 1, SysConstant.HITS_LIMIT_TIME);
        return false;
    }

    @PostMapping(value = "showBlog")
    @ResponseBody
    public BlogShow showBlog(@RequestParam("blogId") String blogIdStr) {
        Integer blogId = BlogStringUtils.getNum(blogIdStr);
        BlogShow blogShow = blogService.findBlogById(blogId);
        log.info("blogShow is {}", JSON.toJSONString(blogShow));
        return blogShow;
    }

    @PostMapping(value = "/addLikes")
    @ResponseBody
    public String addLikes(@RequestParam("blogId") Integer blogId, HttpServletRequest request) {
        String msg = "点赞成功！";
        try {
            BlogShow blogShow = blogService.findBlogById(blogId);
            Integer likesNum = blogShow.getLikes();
            //检查同一ip是否点过赞，没有点赞，则加赞
            if (!checkLikesBySameIp(request, blogId)) {
                log.info("addLikes is success");
                blogService.updateLikes(likesNum + 1, blogId);
                return msg;
            }
            log.info("addLikes is existed");
            msg = "谢谢您，已点过赞了！";
            return msg;
        } catch (Exception e) {
            msg = "系统错误，点赞失败！";
            log.error("addLikes is fail, {}", e);
            return msg;
        }
    }

    /**
     * @param request
     * @param blogId
     * @return true 同一ip已经点赞;false 同一ip没有点赞
     */
    private boolean checkLikesBySameIp(HttpServletRequest request, Integer blogId) {
        String val = IPUtils.getIpAddrByRequest(request) + ":" + blogId;
        Integer count = cache.hget(SysConstant.LIKE_FLAG, val);
        log.info("cache.hget = {}", count);
        if (null != count && count > 0) {
            return true;
        }
        cache.hset(SysConstant.LIKE_FLAG, val, 1);
        return false;
    }
}
