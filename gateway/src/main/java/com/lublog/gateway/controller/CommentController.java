package com.lublog.gateway.controller;

import com.lublog.pojo.Book;
import com.lublog.pojo.Comment;
import com.lublog.pojo.LoginUser;
import com.lublog.service.BookService;
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
    private BookService bookService;

    //展现评论,list不能作为参数
    @RequestMapping("/findComments")
    @ResponseBody
    public List<Comment> findComments(LoginUser user, Book book, HttpSession session){
        List<Comment> comments = commentService.allCommentsById(book.getBid());
        System.out.println(comments);
        return comments;
    }
    //插入评论
    @RequestMapping("/insertComment")
    @ResponseBody
    public String insertComment(String info, LoginUser user, Book book, Comment comment, HttpSession session){
        user=(LoginUser)session.getAttribute("user");
        if (user == null) {
            info="评价失败，请先登录";
            return info;
        }else {
            if (comment.getCcont()=="") {
                info="请输入评价";
                return info;
            }else {
                commentService.insertCommentByid(user.getLuser(), book.getBid(), comment.getCcont());
                bookService.updateComcount(book.getBid());
                info="评价成功";
                return info;
            }

        }



    }
}
