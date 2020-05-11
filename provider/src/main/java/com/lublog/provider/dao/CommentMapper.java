package com.lublog.provider.dao;

import com.lublog.pojo.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: CommentMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:10
 */
public interface CommentMapper {
    //展现一个商品的评论消息
    @Select("select * from comment where bid=#{param2}")
    List<Comment> allCommentsById(int bid);
    //插入评论
    @Insert("insert into comment (luser,bid,ccont) value (#{param1},#{param2},#{param3})")
    void insertCommentByid(String luser, int bid, String ccont);
}
