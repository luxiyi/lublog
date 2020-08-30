package com.lublog.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.lublog.constant.SysConstant;
import com.lublog.po.Plan;
import com.lublog.po.Profile;
import com.lublog.po.Statistics;
import com.lublog.service.*;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: java类作用描述BaseController
 * @Author: lxy
 * @time: 2020/7/17 0:16
 */
@Controller
@Slf4j
public class BaseController {
    private static final String QUERY_COMMENTS_NAME = "comment_count";
    private static final String QUERY_LIKES_NAME = "likes";

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private PlanService planService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String frontIndex() {
        log.info("-------front-index------");
        return "front/index";
    }

    @RequestMapping(value = "/plan")
    public String myLifeShow() {
        log.info("-------------into plan-------------");
        return "front/plan";
    }


    @RequestMapping(value ="/aboutMe", method = RequestMethod.GET)
    public String findProfile(HttpServletRequest request) {
        String title = SysConstant.PROFILE_SPACE;
        Profile profile = profileService.queryProfileByTitle(title);
        log.info("profile is {}", JSON.toJSONString(profile));
        request.setAttribute("profile", profile);
        return "front/about_me";
    }

    @RequestMapping("/login")
    public String loginPage() {
        log.info("-------loginPage-------");
        return "admin/login";
    }

    @RequestMapping(value = "admin/index", method = RequestMethod.GET)
    public String adminIndex(HttpServletRequest request) {
        log.info("-------admin-index------");
        int blogCommentsNum = statisticsService.queryBlogCommentsNum();
        int blogLikesNum = statisticsService.queryBlogLikesNum();
        log.info("blogLikesNum is {}", blogLikesNum);
        int blogTotalNum = statisticsService.queryBlogTotalNum();
        int blogShowNum = statisticsService.queryBlogShowOrDeleteNum(SysConstant.NUM_0);
        int blogDeleteNum = statisticsService.queryBlogShowOrDeleteNum(SysConstant.NUM_1);
        Statistics statistics = new Statistics();
        statistics.setBlogCommentsNum(blogCommentsNum);
        statistics.setBlogLikesNum(blogLikesNum);
        statistics.setBlogTotalNum(blogTotalNum);
        statistics.setBlogShowNum(blogShowNum);
        statistics.setBlogDeleteNum(blogDeleteNum);
        statisticsService.updateStatisticsNum(statistics, SysConstant.NUM_1);
        Statistics newStatistics = statisticsService.queryStatistics(SysConstant.NUM_1);
        log.info("统计数据 statistics = {}", JSON.toJSONString(newStatistics));
        request.setAttribute("statistics", newStatistics);
        return "admin/index";
    }

    @RequestMapping("admin/blogList")
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


    @RequestMapping(value = "/admin/plan")
    public String myLifeManager() {
        log.info("-------------manager plan-------------");
        return "admin/plan";
    }

    @RequestMapping(value = "/justDoIt")
    public String justDoIt(Integer planId,HttpServletRequest request) {
        Plan plan = planService.getOnePlan(planId);
        log.info("plan is {}", plan);
        request.setAttribute("plan",plan);
        log.info("-------------just do it -------------");
        return "front/doPlan";
    }

    @RequestMapping(value = "/creatDoPlan")
    public String creatDoPlan(Integer planId,HttpServletRequest request) {
        Plan plan = planService.getOnePlan(planId);
        log.info("plan is {}", plan);
        request.setAttribute("plan",plan);
        log.info("-------------creat do plan -------------");
        return "admin/creatDoPlan";
    }

    @RequestMapping(value = "/queryBlog", method = RequestMethod.GET)
    public String queryBlog(Integer blogId, HttpServletRequest request) {
        BlogShow blogShow = blogService.findBlogById(blogId);
        request.setAttribute("blogShow", blogShow);
        return "admin/blog_update";
    }

    @RequestMapping(value = "/admin/aboutMe", method = RequestMethod.GET)
    public String aboutMeEdit() {
        log.info("-------------aboutMe edit-------------");
        return "admin/about_me";
    }



}

