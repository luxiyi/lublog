package com.lublog.service;

import com.lublog.po.Comment;

import java.util.List;

/**
 * @Description: CommentServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 21:58
 */
public interface CommentService {
    List<Comment> allCommentsById(int blogId);

    void insertCommentById(String user, int blogId, String ccont);

    int findBidNum(int blogId);

    void deleteCommentsOfBlog(int blogId);
}
