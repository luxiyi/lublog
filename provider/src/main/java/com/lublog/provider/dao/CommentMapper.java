package com.lublog.provider.dao;

import com.lublog.po.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @Description: CommentMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:10
 */
public interface CommentMapper {
    //展现一个评论消息
    @Select("select * from comment where blogid=#{param1} and flag=0")
    List<Comment> allCommentsById(int blogId);

    //插入评论
    @Insert("insert into comment (blogid, observer, commenter, contact, commentcontent, commentdate) value (#{param1},#{param2},#{param3},#{param4},#{param5},#{param6})")
    void insertCommentById(int blogId, String observer, String commenter, String contact, String commentContent, Date commentDate);

    //查找评论过的博客数量
    @Select("select count(blogid) from comment where blogid=#{param1} and flag=0")
    int findBlogNum(int blogId);

    //删除有关该文章的评论
    @Delete("delete from comment where blogid=#{param1} and flag=0")
    void deleteCommentsOfBlog(int blogId);


    @Select("SELECT COUNT(*) total FROM comment where flag = 0")
    int queryAllCommentsTotalPage();

    @Select("select * from comment where flag=0 ORDER BY commentdate desc LIMIT #{param1},#{param2}")
    List<Comment> findAllByIndex(int index, int count);

    @Select("select * from comment where blogid=#{param1} and flag=0 ORDER BY commentdate desc LIMIT #{param2},#{param3}")
    List<Comment> queryOneBlogCommentByIndex(int blogId, int index, int count);

    @Select("SELECT COUNT(*) total FROM comment where blogid=#{param1} and flag = 0")
    int queryOneBlogCommentsCount(int blogId);

    @Update("update comment set flag = 1 where commentId = #{param1}")
    void deleteOneComment(int commentId);

    @Select("SELECT blogid FROM comment where commentid=#{param1} and flag = 0")
    int queryOneBlogOfComment(int commentId);

    @Select("select count(*) from comment where flag = 0")
    int queryCommentAllCount();

}
