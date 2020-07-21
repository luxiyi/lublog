package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lublog.dto.BlogCategory;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.service.UserService;
import com.lublog.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述BaseController
 * @Author: lxy
 * @time: 2020/7/17 0:16
 */
@Controller
@Slf4j
public class BaseController {
    @Autowired
    private BlogService blogService;

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
        return "/admin/login";
    }

    @RequestMapping("/admin/articleList")
    public String articleList() {
        log.info("-------admin-articleList-------");
        return "admin/article_list";
    }

    @RequestMapping(value = "/admin/articleEdit", method = RequestMethod.GET)
    public String articleEdit() {
        log.info("-------admin-articleEdit------");
        return "/admin/article_edit";
    }

    @RequestMapping(value = "/admin/commentList")
    public String commentList() {
        log.info("-------admin-commentList------");
        return "/admin/comment_list";
    }


    @RequestMapping(value = "/admin/categoryList")
    public String categoryList() {
        log.info("-------admin-categoryList------");
        return "/admin/category_list";
    }

    @RequestMapping(value = "/detail")
    public String bookDetail() {
        log.info("-------------books detail-------------");
        return "detail";
    }

    @RequestMapping(value = "blogCategories")
    public String blogCategories(HttpServletRequest request, @RequestParam("categoryId") int categoryId) throws ParseException {
        List<BlogCategory> blogCategories = blogService.getBlogByCategories(categoryId);
        String catoryPattern = "yyyy-MM";
        String blogPattern = "yyyy年MM月dd日";
        if (CollectionUtils.isNotEmpty(blogCategories)) {
            for (BlogCategory blogCategory: blogCategories) {
                SimpleDateFormat sf = new SimpleDateFormat(catoryPattern);
                Date startDate = sf.parse(blogCategory.getDate());
                Date endDate = DateUtils.formatDate(DateUtils.dateAdd(DateUtils.INTERVAL_MONTH, startDate, 1),catoryPattern);
                log.info("startDate = {},endDate = {}",startDate,endDate);
                List<BlogContent> blogContents = blogService.getBlogsByCategories(categoryId, startDate, endDate);
                for (BlogContent blogContent: blogContents) {
                    blogContent.setPubdate(DateUtils.formatDate(blogContent.getPubdate(),blogPattern));
                }
                log.info("blogContents size = {},blogContents is {}",blogContents.size(), blogContents);
                sf = new SimpleDateFormat("yyyy年MM月");
                String newDate = sf.format(startDate);

                blogCategory.setBlogs(blogContents);
                blogCategory.setDate(newDate);
                log.info("blogCategories size = {}, blogCategories is {}", blogCategories.size(), Arrays.asList(blogCategories));
            }
        }
        log.info("categoryId={},blogCategories ={}", categoryId, Arrays.asList(blogCategories));
        request.setAttribute("blogCategories", blogCategories);
        return "front/blogCategories";
    }

}
