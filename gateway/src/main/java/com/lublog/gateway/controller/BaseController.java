package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lublog.dto.BlogCategory;
import com.lublog.dto.BlogTag;
import com.lublog.po.BlogContent;
import com.lublog.po.Plan;
import com.lublog.po.Tag;
import com.lublog.po.User;
import com.lublog.service.*;
import com.lublog.utils.DateUtils;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: java类作用描述BaseController
 * @Author: lxy
 * @time: 2020/7/17 0:16
 */
@Controller
@Slf4j
public class BaseController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String frontIndex() {
        log.info("-------front-index------");
        return "front/index";
    }

    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String adminIndex() {
        log.info("-------admin-index------");
        return "admin/index";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        log.info("-------loginPage-------");
        return "admin/login";
    }

    @RequestMapping("/admin/blogList")
    public String blogList() {
        log.info("-------admin-blogList-------");
        return "admin/blog_list";
    }

    @RequestMapping(value = "/admin/blogEdit", method = RequestMethod.GET)
    public String blogEdit() {
        log.info("-------admin-blogEdit------");
        return "admin/blog_edit";
    }

    @RequestMapping(value = "/admin/commentList")
    public String commentList(HttpServletRequest request) {
        int commentAllCount = commentService.queryCommentAllCount();
        request.setAttribute("commentAllCount", commentAllCount);
        log.info("commentAllCount is {}", commentAllCount);
        log.info("-------admin-commentList------");
        return "admin/comment_list";
    }


    @RequestMapping(value = "/admin/categoryList")
    public String categoryList() {
        log.info("-------admin-categoryList------");
        return "admin/category_list";
    }

    @RequestMapping(value = "/front/blog")
    public String blogDetail() {
        log.info("-------------blog detail-------------");
        return "front/blog";
    }

    @RequestMapping(value = "/plan")
    public String myLifeShow() {
        log.info("-------------into plan-------------");
        return "front/plan";
    }

    @RequestMapping(value = "/admin/plan")
    public String myLifeManager() {
        log.info("-------------manager plan-------------");
        return "admin/plan";
    }

    @RequestMapping(value = "/justDoIt")
    public String justDoIt(Integer planId,HttpSession session) {
        Plan plan = planService.getOnePlan(planId);
        log.info("plan is {}", plan);
        session.setAttribute("plan",plan);
        log.info("-------------just do it -------------");
        return "front/doPlan";
    }

    @RequestMapping(value = "/creatDoPlan")
    public String creatDoPlan() {
        log.info("-------------creat do plan -------------");
        return "admin/creatDoPlan";
    }

    @RequestMapping(value = "/queryBlog", method = RequestMethod.GET)
    public String queryArticle(Integer blogId, HttpServletRequest request) {
        BlogShow blogShow = blogService.findBlogById(blogId);
        request.setAttribute("blog", blogShow);
        return "admin/blog_update";
    }

    @RequestMapping(value = "/aboutMe", method = RequestMethod.GET)
    public String findMe() {
        log.info("-------------about me -------------");
        return "front/about_me";
    }
}

