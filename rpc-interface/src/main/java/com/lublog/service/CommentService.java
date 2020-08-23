package com.lublog.service;

import com.lublog.po.Comment;

import java.util.Date;
import java.util.List;

/**
 * @Description: CommentServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 21:58
 */
public interface CommentService {
    List<Comment> allCommentsById(int blogId);

    void insertCommentById(int blogId, String observer, String commenter, String contact, String commentContent, Date commentDate);

    int findBidNum(int blogId);

    void deleteCommentsOfBlog(int blogId);

    int queryAllCommentsTotalPage();

    List<Comment> findAllByIndex(int index, int count);

    List<Comment> queryOneBlogCommentByIndex(Integer blogId, int index, int i);

    int queryOneBlogCommentTotalPage(int blogId);

    void deleteOneComment(Integer commentId);

    int queryOneBlogOfComment(Integer commentId);

    int queryOneBlogCommentsCount(int blogId);

    int queryCommentAllCount();
}
