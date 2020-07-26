package com.lublog.gateway.controller.admin;

import com.lublog.po.Comment;
import com.lublog.service.BlogService;
import com.lublog.service.CommentService;
import com.lublog.utils.DateUtils;
import com.lublog.vo.BlogShow;
import com.lublog.vo.CommentShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述CommetController
 * @Author: lxy
 * @time: 2020/7/17 0:23
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class CommetController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/showAllComments", method = RequestMethod.GET)
    public Map<String, Object> showAllComments(HttpServletRequest request, Integer page, Integer totalPage) {
        Map<String, Object> result = new HashMap<String, Object>();
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        page = (Integer) request.getAttribute("page");
        totalPage = commentService.queryAllCommentsTotalPage();
        int index = (page - 1) * 12;
        List<Comment> commentShows = commentService.findAllByIndex(index, 12);

        log.info("totalPage = {}, page = {}, comments = {}", totalPage, page, commentShows.toString());
        request.setAttribute("commentShows", commentShows);
        result.put("commentShows", commentShows);
        result.put("totalPage", totalPage);
        return result;
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.DELETE)
    public String deleteComment(@RequestParam("commentId") Integer commentId, HttpSession session) throws SQLException {
        String msg = "删除评论失败";
        if (commentId == 0 || commentId == null) {
            log.error("delete comment fail ,commentId is {}", commentId);
            return msg;
        }

        int blogId = commentService.queryOneBlogOfComment(commentId);
        if (blogId == 0) {
            log.error("delete comment fail ,blogId is {}", blogId);
            return msg;
        }

        /*
         * 删除一个评论，减少一个该文章的评论数量。
         * 防止评论数量为0，但评论还在
         */
        try {

            commentService.deleteOneComment(commentId);
            int blogCommentsCount = commentService.queryOneBlogCommentsCount(blogId);
            if (blogCommentsCount == 0 || blogCommentsCount < 0) {
                log.info("does not reduce comment fail ,blogCommentsCount has already is {}", blogCommentsCount);
                msg = "删除评论成功";
                return msg;
            }
            blogService.reduceCommentCount(blogId);
        } catch (Exception e) {
            log.error("delete comment or reduce commentNum fail is {}", e);
            return msg;
        }
        msg = "删除评论成功";
        log.info("delete comment success");
        return msg;
    }


}
