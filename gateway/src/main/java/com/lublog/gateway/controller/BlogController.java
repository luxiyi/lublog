package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.lublog.po.BlogContent;
import com.lublog.po.LoginUser;
import com.lublog.service.BlogService;
import com.lublog.service.CartService;
import com.lublog.service.CommentService;
import com.lublog.service.UserService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
public class BlogController {
//    private Logger LOG = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("findAllBlog")
    public Map<String, Object> findAllBlog(HttpSession session, Integer page, Integer totalPage, BlogShow findBlogContent, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        // 获取页码、总页码
        page = (int) session.getAttribute("page");
        totalPage = blogService.findTotalPage();
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

    // 传入参数并跳转页面
    @RequestMapping("sendDatil")
    public ModelAndView sendDatil(BlogContent blogContent, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        // 要跳转的页面
        mav.setViewName("front/detail");
        // 传入对象
        mav.addObject("blogContent", blogContent);
        log.info("sendDetail，bookid = {}", blogContent.getBlogid());
        BlogShow findBlogContent = blogService.findBookById(blogContent.getBlogid());
        session.setAttribute("blogContent", findBlogContent);
        BlogShow com = (BlogShow) session.getAttribute("blogContent");
        log.info("blogContent = {}",findBlogContent);
        log.info("blogContent.commentcount = {}",com.getCommentcount());
        return mav;

    }


    // 采用超链接跳转传参到商品详情页面，最主要是获取传过去对象bid的值
    @RequestMapping("findOneBook")
    public BlogShow findOneBook(HttpServletRequest request) {
        log.info("------findOneBook------");
        int bid = Integer.parseInt(request.getParameter("blogid"));
        BlogShow blogShow = blogService.findBookById(bid);
        log.info("blogContent.commentcount = {}",blogShow.getCommentcount());
        return blogShow;
    }

    // 搜索商品
    @RequestMapping("findBlog")
    public List<BlogShow> findBlog(HttpSession session, String findTitle) {
        session.setAttribute("findTitle", findTitle);
        List<BlogShow> BlogContents = new ArrayList<>();
        BlogContents = blogService.findlikeBook(findTitle);
        log.info("findBlog, title = {}", findTitle);
        return BlogContents;
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

    @RequestMapping(value = "/detail")
    public String bookDetail() {
        log.info("-------------books detail-------------");
        return "detail";
    }

    /**
     * 保存发布文章
     */
    @RequestMapping(value = "/pushBlog", method = RequestMethod.POST)
    public String saveOrUpdateBlog(@RequestParam("title") String title, @RequestParam("content") String content,
                                   @RequestParam("categoryId") String categoryIdStr) {
        int categoryId = Integer.parseInt(categoryIdStr);
        BlogContent blogContent = new BlogContent();
        blogContent.setTitle(title);
        blogContent.setContent(content);
        blogContent.setPubdate(new Date());
        blogContent.setCategoryid(categoryId);
        JSONObject result = new JSONObject();
        try {
            this.blogService.insertBlog(blogContent);
            result.put("result", "success");
            return result.toJSONString();
        } catch (Exception e) {
            log.error("blog push failed is {}", e);
            result.put("result", "error");
            return result.toJSONString();
        }
    }
}
