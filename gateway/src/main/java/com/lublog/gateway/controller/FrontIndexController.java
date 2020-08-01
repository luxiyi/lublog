package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.dto.BlogCategory;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.service.CartService;
import com.lublog.service.CommentService;
import com.lublog.service.UserService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Description: java类作用描述BookController
 * @Author: lxy
 * @time: 2020/4/12 22:49
 */
@SuppressWarnings("all")
@Slf4j
@RestController
public class FrontIndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "findAllBlog", method = RequestMethod.GET)
    public Map<String, Object> findAllBlog(HttpSession session, Integer page, Integer totalPage, BlogShow findBlogContent, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        // 获取页码、总页码
        page = (int) session.getAttribute("page");
        totalPage = (int)blogService.findBlogTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        List<BlogShow> blogContents = blogService.findAllByIndex(index, 12);
        BlogShow blogContent = blogService.findAllById(findBlogContent);
        log.info("totalPage = {}, page = {}, books = {}", totalPage, page, blogContents.toString());
        // 将所有书放入session
        session.setAttribute("blogContents", blogContents);
        session.setAttribute("blogContent", blogContent);
        session.setAttribute("bbid", bbid);
        result.put("blogContents", blogContents);
        result.put("totalPage", totalPage);
        return result;
    }


//    @RequestMapping("addBooks")
//    public String addBooks(LoginUser user, BlogShow newBlog, HttpSession session, String info) {
//        // 判断非空
//        user = (LoginUser) session.getAttribute("admin");
//        if (user == null) {
//            info = "请先登录";
//            LOG.error("you are not login");
//            return info;
//        }
//        if (StringUtils.isEmpty(newBlog.getTitle()) || StringUtils.isEmpty(newBlog.getBlogcover())
//                || StringUtils.isEmpty(newBlog.getAuthor()) ) {
//            LOG.error("add books failed, some fileds is null");
//            info = "添加博客失败，字段不能为空";
//            return info;
//        }
//        BlogShow isTitleExist = blogService.findAllById(newBlog);// 判断是否存在
//        if (null == isTitleExist) {
//            blogService.insertBook(newBlog);
//            LOG.info("add books success");
//            info = "添加成功";
//            return info;
//        } else {
//            LOG.error("add books failed, some fileds is error");
//            info = "添加图书失败，请输入正确字段";
//            return info;
//        }
//    }

    // 通过找到bid删除一本书籍
    @RequestMapping("deleteBooks")
    public String deleteBooks(HttpSession session, BlogShow deleteBlogContent) {
        String info = "删除失败，请联系管理员";

        Object user = session.getAttribute("admin");
        if (user == null) {
            info = "请先登录";
            log.error("not login, please login first");
            return info;
        }
        if (null == deleteBlogContent) {
            log.error("deleteBlogContent is null");
            return info;
        }
        String title = deleteBlogContent.getTitle();
        if (StringUtils.isEmpty(title)){
            log.error("this {} title is null", deleteBlogContent.getBlogid());
            return info;
        }
        log.info("this title = {}", title);
        BlogShow findBlogContent = blogService.findAllById(deleteBlogContent);
        if (null == findBlogContent) {
            log.error("this blog is not exist, delete blog failed");
            return info;
        }
        //删除博客，先删除评论表中的
        int blogId = findBlogContent.getBlogid();
        int commentNum = commentService.findBidNum(blogId);
        log.info("this {} commentNum is {}", findBlogContent.getTitle(), commentNum);
        if (commentNum > 0) {
            commentService.deleteCommentsOfBlog(blogId);
            return info;
        }
        //todo 删除失败抛异常
        blogService.deleteById(blogId);
        info = "删除成功";
        return info;
    }

    // 修改书籍首先找到书籍的内容
    @RequestMapping("queryBooks")
    public Map<String, Object> queryBooks(HttpSession session, String info, BlogShow blogContent) {
        Map<String, Object> result = new HashMap<String, Object>();
        Object obj = session.getAttribute("admin");
        if (obj == null) {
            info = "请先登录";
            result.put("info", info);
            log.info("you are not login");
            return result;
        }
        log.info("bookid = {}", blogContent.getBlogid());
        blogContent = blogService.findAllById(blogContent);
        result.put("book", blogContent);
        return result;


    }

//    // 修改书籍内容
//    @RequestMapping("updateBooks")
//    public String updateBooks(BlogShow blogContent, String info) {
//        if (blogContent == null) {
//            LOG.error("modify book failed");
//            info = "更新图书失败，请重新输入字段";
//            return info;
//        }
//        // 通过传过来的bid判断需要更新的书籍是否存在
//        BlogContent isexit = blogService.findAllById(blogContent);
//        // 不存在新的书，就更新
//        if (isexit != null) {
//            blogService.updateById(blogContent);
//            info = "修改成功";
//            return info;
//        } else {
//            info = "更新图书失败，请重新输入字段";
//            return info;
//        }
//    }




}
