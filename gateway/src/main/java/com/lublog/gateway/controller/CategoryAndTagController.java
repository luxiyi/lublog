package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lublog.dto.BlogCategory;
import com.lublog.dto.BlogTag;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.service.CategoryService;
import com.lublog.service.TagService;
import com.lublog.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.PATTERN;

/**
 * @Description: java类作用描述CategoryController
 * @Author: lxy
 * @time: 2020/7/25 1:15
 */
@Slf4j
@Controller
public class CategoryAndTagController {
    private static final String PATTERN = "yyyy-MM";
    private static final String BLOGPATTERN = "yyyy年MM月dd日";
    private static final String NEWPATTERN = "yyyy年MM月";


    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/blogCategories")
    public String blogCategories(HttpServletRequest request, @RequestParam("categoryId") int categoryId) throws ParseException {
        String categoryName = categoryService.findNameById(categoryId);
        List<BlogCategory> blogCategories = blogService.getBlogByCategories(categoryId);
        if (CollectionUtils.isNotEmpty(blogCategories)) {
            for (BlogCategory blogCategory : blogCategories) {
                String dateStr = blogCategory.getDate();
                blogCategory.setBlogs(setBlogContentsPubdate(getBlogContentsByCategories(dateStr, PATTERN, categoryId), BLOGPATTERN));
                log.info("blogCategories size = {}, blogCategories is {}", blogCategories.size(), Arrays.asList(blogCategories));
            }
        }
        log.info("categoryId={},blogCategories ={}", categoryId, Arrays.asList(blogCategories));
        request.setAttribute("blogCategories", blogCategories);
        request.setAttribute("categoryName", categoryName);
        return "front/blogCategories";
    }

    @RequestMapping(value = "/blogTags")
    public String blogTags(HttpServletRequest request, @RequestParam("tagId") int tagId) {
        String tagName = tagService.findNameById(tagId);
        List<BlogTag> blogTags = blogService.getBlogByTags(tagId);
        if (CollectionUtils.isNotEmpty(blogTags)) {
            for (BlogTag blogTag : blogTags) {
                String dateStr = blogTag.getDate();
                blogTag.setBlogs(setBlogContentsPubdate(getBlogContentsByTags(dateStr, PATTERN, tagId), BLOGPATTERN));
                blogTag.setDate(getNewDate(PATTERN, NEWPATTERN, dateStr));
            }
        }
        log.info("blogTags size = {},categoryId={},blogCategories ={}", blogTags.size(), tagId, Arrays.asList(blogTags));
        request.setAttribute("blogTags", blogTags);
        request.setAttribute("tagName", tagName);
        return "front/blogTags";
    }

    private List<BlogContent> setBlogContentsPubdate(List<BlogContent> blogContents, String blogPattern) {
        for (BlogContent blogContent : blogContents) {
            blogContent.setPubdate(DateUtils.formatDate(blogContent.getPubdate(), blogPattern));
        }
        log.info("blogContents size = {},blogContents is {}", blogContents.size(), blogContents);
        return blogContents;
    }

    private List<BlogContent> getBlogContentsByCategories(String dateStr, String pattern, int id) {
        Date endDate = DateUtils.formatDate(DateUtils.dateAdd(DateUtils.INTERVAL_MONTH, getStartDate(pattern, dateStr), 1), pattern);
        log.info("startDate = {},endDate = {}", getStartDate(pattern, dateStr), endDate);
        List<BlogContent> blogContents = blogService.getBlogsByCategories(id, getStartDate(pattern, dateStr), endDate);
        return blogContents;
    }

    private List<BlogContent> getBlogContentsByTags(String dateStr, String pattern, int id) {
        Date endDate = DateUtils.formatDate(DateUtils.dateAdd(DateUtils.INTERVAL_MONTH, getStartDate(pattern, dateStr), 1), pattern);
        log.info("startDate = {},endDate = {}", getStartDate(pattern, dateStr), endDate);
        List<BlogContent> blogContents = blogService.getBlogsByTags(id, getStartDate(pattern, dateStr), endDate);
        return blogContents;
    }

    private String getNewDate(String pattern, String newPattern, String dateStr) {
        Date startDate = getStartDate(pattern, dateStr);
        SimpleDateFormat sf = new SimpleDateFormat(newPattern);
        String newDate = sf.format(startDate);
        return newDate;
    }

    private static Date getStartDate(String pattern, String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        Date startDate = null;
        try {
            startDate = sf.parse(dateStr);
        } catch (ParseException e) {
            log.error("string parse date fail is {}", e);
        }
        return startDate;
    }
}
