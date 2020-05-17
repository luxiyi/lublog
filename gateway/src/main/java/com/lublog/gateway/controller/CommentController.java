package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.po.BlogContent;
import com.lublog.po.Comment;
import com.lublog.po.LoginUser;
import com.lublog.service.BlogService;
import com.lublog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: java类作用描述CommentController
 * @Author: lxy
 * @time: 2020/4/16 0:47
 */
@Controller
public class CommentController {
    private Logger LOG = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    //展现评论,list不能作为参数
    @RequestMapping("/findComments")
    @ResponseBody
    public List<Comment> findComments(LoginUser user, BlogContent book, HttpSession session){
        List<Comment> comments = commentService.allCommentsById(book.getBlogid());
        System.out.println(comments);
        return comments;
    }
    //插入评论
    @RequestMapping("/insertComment")
    @ResponseBody
    public String insertComment(BlogContent blogContent, Comment comment, HttpSession session) {
        String info = "评价失败";
        LoginUser user = (LoginUser) session.getAttribute("admin");
        if (user == null) {
            info = "请先登录";
            return info;
        }
        if (StringUtils.isEmpty(comment.getCommentcontent())) {
            return info;
        }
        commentService.insertCommentById(user.getLuser(), blogContent.getBlogid(), comment.getCommentcontent());
        blogService.updateComcount(blogContent.getBlogid());
        info = "评价成功";
        return info;


    }
}
