package com.lublog.provider.dao;

import com.lublog.po.Comment;
import com.lublog.vo.CommentShow;
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

    //展现评论消息
    @Select("SELECT b.blogid,b.title,c.commentcontent,c.commentid,c.commentdate,c.user " +
            " FROM blogcontent b INNER JOIN comment c ON b.blogid = c.blogid" +
            " where b.flag=0 AND c.flag=0 ORDER BY c.commentdate desc LIMIT #{param1},#{param2}")
    List<CommentShow> showLastComment(int index, int count);

    @Select("SELECT COUNT(*) total FROM comment where flag = 0")
    int findTotalPage();

    @Select("SELECT b.blogid,b.title,c.commentcontent,c.commentid,c.commentdate,c.user " +
            " FROM blogcontent b INNER JOIN comment c ON b.blogid = c.blogid" +
            " where b.flag=0 AND c.flag=0 ORDER BY c.commentdate desc LIMIT #{param1},#{param2}")
    List<CommentShow> findAllByIndex(int index, int count);

    @Select("SELECT b.blogid,b.title,c.commentcontent,c.commentid,c.commentdate,c.user " +
            " FROM blogcontent b INNER JOIN comment c ON b.blogid = c.blogid" +
            " where c.commentid = #{param1} AND b.flag=0 AND c.flag=0 ORDER BY c.commentdate desc")
    CommentShow findAllById(CommentShow commentShow);
}
