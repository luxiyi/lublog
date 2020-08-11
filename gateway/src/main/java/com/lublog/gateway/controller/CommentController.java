package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.po.Comment;
import com.lublog.service.BlogService;
import com.lublog.service.CommentService;
import com.lublog.utils.BlogStringUtils;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Description: java类作用描述CommentController
 * @Author: lxy
 * @time: 2020/4/16 0:47
 */
@RestController
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    //展现评论,list不能作为参数
    @RequestMapping(value = "/findComments", method = RequestMethod.GET)
    public Map<String, Object> findComments(@RequestParam("blogId") String blogIdStr, HttpServletRequest request, Integer page, Integer totalPage) {
        log.info("blogIdStr is {}", blogIdStr);
        Integer blogId = BlogStringUtils.getNum(blogIdStr);
        Map<String, Object> result = new HashMap<String, Object>();
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        page = (Integer) request.getAttribute("page");
        totalPage = commentService.queryOneBlogCommentTotalPage(blogId);
        int index = (page - 1) * 12;
        List<Comment> comments = commentService.queryOneBlogCommentByIndex(blogId,index, 12);
        log.info("totalPage = {}, page = {}, comments = {}", totalPage, page, comments.toString());
        request.setAttribute("comments", comments);
        result.put("comments", comments);
        result.put("totalPage", totalPage);
        log.info("comments is {}", Arrays.asList(comments));
        return result;
    }

    //插入评论
    @RequestMapping(value = "/addBlogComment", method = RequestMethod.POST)
    public String addBlogComment(HttpSession session, String observer, String contact, String commentContent, @RequestParam("blogId") String blogIdStr) {
        String info = "评论成功";

        if (StringUtils.isEmpty(blogIdStr) || blogIdStr == ""
                || StringUtils.isEmpty(observer) || observer == ""
                || StringUtils.isEmpty(contact) || contact == ""
                || StringUtils.isEmpty(commentContent) || commentContent == "") {
            info = "评论失败，请联系管理员";
            log.error("comment push fail, blogIdStr is {}", blogIdStr);
            return info;
        }
        Integer blogId = BlogStringUtils.getNum(blogIdStr);
        BlogShow blogShow = blogService.findBlogById(blogId);
        if (blogShow == null || StringUtils.isEmpty(blogShow.getAuthor()) || blogShow.getAuthor() == "") {
            info = "评论失败，请联系管理员";
            log.error("query blogShow fail，blogShow is {}", blogShow);
            return info;
        }
        String commenter = blogShow.getTitle();
        Date commentDate = new Date();
        log.info("blogId is {}, observer is {}, contact is {}, commentContent is {}, commentDate is {}",
                blogId, observer, contact, commentContent, commentDate);
        try {
            commentService.insertCommentById(blogId, observer, commenter, contact, commentContent, commentDate);
            blogService.updateComcount(blogId);
        } catch (Exception e) {
            info = "评论失败，请联系管理员";
            log.error("comment push fail is {}", e);
            return info;
        }
        log.info("insert comment success, contact is {}, commentContent is {}", contact, commentContent);
        return info;
    }

}
