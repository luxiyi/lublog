package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lublog.dto.BlogCategory;
import com.lublog.dto.BlogTag;
import com.lublog.po.BlogContent;
import com.lublog.po.Tag;
import com.lublog.service.BlogService;
import com.lublog.service.CategoryService;
import com.lublog.service.TagService;
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

    @RequestMapping(value = "/front/blog")
    public String bookDetail() {
        log.info("-------------blog detail-------------");
        return "/front/blog";
    }

}

