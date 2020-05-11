package com.lublog.service;

import com.lublog.pojo.Comment;

import java.util.List;

/**
 * @Description: CommentServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 21:58
 */
public interface CommentService {
    List<Comment> allCommentsById(int bid);
    void insertCommentByid(String luser, int bid, String ccont);
}
