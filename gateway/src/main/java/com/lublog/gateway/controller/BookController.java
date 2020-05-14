package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.po.BlogContent;
import com.lublog.po.LoginUser;
import com.lublog.service.BookService;
import com.lublog.service.CartService;
import com.lublog.service.CommentService;
import com.lublog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述BookController
 * @Author: lxy
 * @time: 2020/4/12 22:49
 */
@SuppressWarnings("all")
@Controller
public class BookController {
    private Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("findAllBlog")
    @ResponseBody
    public Map<String, Object> findAllBlog(HttpSession session, Integer page, Integer totalPage, BlogContent findBlogContent, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        // 获取页码、总页码
        page = (int) session.getAttribute("page");
        totalPage = bookService.findTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        List<BlogContent> blogContents = bookService.findAllByIndex(index, 12);
        BlogContent blogContent = bookService.findAllById(findBlogContent);
        LOG.info("totalPage = {}, page = {}, books = {}", totalPage, page, blogContents.toString());
        // 将所有书放入session
        session.setAttribute("blogContents", blogContents);
        session.setAttribute("blogContent", blogContent);
        session.setAttribute("bbid", bbid);
        result.put("blogContents", blogContents);
        result.put("totalPage", totalPage);
        return result;
    }

    // 传入参数并跳转页面
    @RequestMapping("sendDatil")
    public ModelAndView sendDatil(BlogContent blogContent) {
        ModelAndView mav = new ModelAndView();
        // 要跳转的页面
        mav.setViewName("/detail");
        // 传入对象
        mav.addObject("blogContent", blogContent);
        LOG.info("sendDetail，bookid = {}", blogContent.getBlogid());
        return mav;

    }


    // 采用超链接跳转传参到商品详情页面，最主要是获取传过去对象bid的值
    @RequestMapping("findOneBook")
    @ResponseBody
    public BlogContent findOneBook(HttpSession session, HttpServletRequest request) {
        LOG.info("------findOneBook------");
        int bid = Integer.parseInt(request.getParameter("blogid"));
        BlogContent blogContent = bookService.findBookById(bid);
        return blogContent;
    }

    // 搜索商品
    @RequestMapping("findBlog")
    @ResponseBody
    public List<BlogContent> findBlog(HttpSession session, String findTitle) {
        session.setAttribute("findTitle", findTitle);
        List<BlogContent> BlogContents = new ArrayList<>();
        BlogContents = bookService.findlikeBook(findTitle);
        LOG.info("findBlog, title = {}", findTitle);
        return BlogContents;
    }

    @RequestMapping("addBooks")
    @ResponseBody
    public String addBooks(LoginUser user, BlogContent newBlog, HttpSession session, String info) {
        // 判断非空
        user = (LoginUser) session.getAttribute("user");
        if (user == null) {
            info = "请先登录";
            LOG.error("you are not login");
            return info;
        }
        if (StringUtils.isEmpty(newBlog.getTitle()) || StringUtils.isEmpty(newBlog.getBlogcover())
                || StringUtils.isEmpty(newBlog.getAuthor()) || StringUtils.isEmpty(newBlog.getPress())
                || newBlog.getBcount() == 0 || newBlog.getPrice() == 0) {
            LOG.error("add books failed, some fileds is null");
            info = "添加博客失败，字段不能为空";
            return info;
        }
        BlogContent isTitleExist = bookService.findAllById(newBlog);// 判断是否存在
        if (null == isTitleExist) {
            bookService.insertBook(newBlog);
            LOG.info("add books success");
            info = "添加成功";
            return info;
        } else {
            LOG.error("add books failed, some fileds is error");
            info = "添加图书失败，请输入正确字段";
            return info;
        }
    }

    // 通过找到bid删除一本书籍
    @RequestMapping("deleteBooks")
    @ResponseBody
    public String deleteBooks(HttpSession session, BlogContent deleteBlogContent) {
        String info = "删除失败，请联系管理员";

        Object user = session.getAttribute("user");
        if (user == null) {
            info = "请先登录";
            LOG.error("not login, please login first");
            return info;
        }
        if (null == deleteBlogContent) {
            LOG.error("deleteBlogContent is null");
            return info;
        }
        String title = deleteBlogContent.getTitle();
        if (StringUtils.isEmpty(title)){
            LOG.error("this {} title is null", deleteBlogContent.getBlogid());
            return info;
        }
        LOG.info("this title = {}", title);
        BlogContent findBlogContent = bookService.findAllById(deleteBlogContent);
        if (null == findBlogContent) {
            LOG.error("this blog is not exist, delete blog failed");
            return info;
        }
        //删除博客，先删除评论表中的
        int blogId = findBlogContent.getBlogid();
        int commentNum = commentService.findBidNum(blogId);
        LOG.info("this {} commentNum is {}", findBlogContent.getTitle(), commentNum);
        if (commentNum > 0) {
            commentService.deleteCommentsOfBlog(blogId);
            return info;
        }
        //todo 删除失败抛异常
        bookService.deleteById(blogId);
        info = "删除成功";
        return info;
    }

    // 修改书籍首先找到书籍的内容
    @RequestMapping("queryBooks")
    @ResponseBody
    public Map<String, Object> queryBooks(HttpSession session, String info, BlogContent blogContent) {
        Map<String, Object> result = new HashMap<String, Object>();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            info = "请先登录";
            result.put("info", info);
            LOG.info("you are not login");
            return result;
        }
        LOG.info("bookid = {}", blogContent.getBlogid());
        blogContent = bookService.findAllById(blogContent);
        result.put("book", blogContent);
        return result;


    }

    // 修改书籍内容
    @RequestMapping("updateBooks")
    @ResponseBody
    public String updateBooks(BlogContent blogContent, String info) {
        if (blogContent == null) {
            LOG.error("modify book failed");
            info = "更新图书失败，请重新输入字段";
            return info;
        }
        // 通过传过来的bid判断需要更新的书籍是否存在
        BlogContent isexit = bookService.findAllById(blogContent);
        // 不存在新的书，就更新
        if (isexit != null) {
            bookService.updateById(blogContent);
            info = "修改成功";
            return info;
        } else {
            info = "更新图书失败，请重新输入字段";
            return info;
        }
    }

    @RequestMapping(value = "/detail")
    public String bookDetail() {
        LOG.info("-------------books detail-------------");
        return "detail";
    }

}
