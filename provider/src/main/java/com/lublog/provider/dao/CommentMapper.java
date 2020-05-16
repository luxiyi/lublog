package com.lublog.provider.dao;

import com.lublog.po.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: CommentMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:10
 */
public interface CommentMapper {
    //展现一个评论消息
    @Select("select * from comment where blogid=#{param2}")
    List<Comment> allCommentsById(int blogId);

    //插入评论
    @Insert("insert into comment (admin,blogid,commentcontent) value (#{param1},#{param2},#{param3})")
    void insertCommentByid(String user, int blogId, String commentContent);

    //查找评论过的博客数量
    @Select("select count(blogid) from comment where blogid=#{param1}")
    int findBlogNum(int blogId);

    //删除有关该文章的评论
    @Delete("delete from comment where ")
    void deleteCommentsOfBlog(int blogId);
}
