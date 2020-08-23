package com.lublog.provider.dao;

import com.lublog.dto.BlogCategory;
import com.lublog.dto.BlogTag;
import com.lublog.po.BlogContent;
import com.lublog.vo.BlogShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @Description: BookMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:08
 */
public interface BlogMapper {
    //根据页码查找全部博客
    @Select("SELECT b.blog_id,b.title,b.author,b.content,b.publish_time,b.blog_cover,b.introduce,b.likes,b.views,b.comment_count,t.tag_name,c.category_name " +
            "FROM t_blogContent b INNER JOIN t_tag t ON b.tag_id = t.tag_id INNER JOIN t_category c ON b.category_id = c.category_id WHERE b.flag=0 AND t.flag=0 AND c.flag=0 " +
            "ORDER BY b.publish_time desc limit #{param1},#{param2}")
    List<BlogShow> findALLByIndex(int index, int count);

    //根据页码查找全部博客
    @Select("SELECT b.blog_id,b.title,b.author,b.content,b.publish_time,b.blog_cover,b.introduce,b.likes,b.views,b.comment_count,t.tag_name,c.category_name " +
            "FROM t_blogContent b INNER JOIN t_tag t ON b.tag_id = t.tag_id INNER JOIN t_category c ON b.category_id = c.category_id WHERE b.flag=0 AND t.flag=0 AND c.flag=0 " +
            "ORDER BY b.publish_time desc limit #{param1},#{param2}")
    List<BlogShow> showLastArticle(int index, int count);

    //查询博客总条数
    @Select("SELECT COUNT(*) total FROM t_blogContent where flag = 0")
    int findBlogTotalPage();

    //增加新博客
    @Insert("insert into t_blogContent (title,author,publish_time,blog_cover,introduce,content,tag_id,category_id) values (#{title},#{author},#{publishTime},#{blogCover},#{introduce},#{content},#{tagId},#{categoryId})")
    void insertBook(BlogContent blogContent);

    //根据blog_id查找博客
    @Select("SELECT b.blog_id,b.title,b.author,b.content,b.publish_time,b.blog_cover,b.introduce,b.likes,b.views,b.comment_count,t.tag_name,c.category_name " +
            "FROM t_blogContent b INNER JOIN t_tag t ON b.tag_id = t.tag_id INNER JOIN t_category c ON b.category_id = c.category_id " +
            "where blog_id=#{blogId} AND b.flag=0 AND t.flag=0 AND c.flag=0")
    BlogShow findAllById(BlogShow blogShow);

    @Select("SELECT b.blog_id,b.title,b.author,b.content,b.publish_time,b.blog_cover,b.introduce,b.likes,b.views,b.comment_count,t.tag_name,c.category_name " +
            "FROM t_blogContent b INNER JOIN t_tag t ON b.tag_id = t.tag_id INNER JOIN t_category c ON b.category_id = c.category_id " +
            "where blog_id=#{blogId} AND b.flag=0 AND t.flag=0 AND c.flag=0")
    BlogShow findBlogById(int blogId);

    //根据博客标题判断是否存在
    @Select("select title from t_blogContent where title=#{title} and flag =0")
    BlogContent findSameTitle(String newTitle);

    //删除博客
    @Update("update t_blogContent set flag = 1 where blog_id = #{blogId} and flag =0")
    void deletById(int blogId);

    //查询全部的博客
    @Select("SELECT b.blog_id,b.title,b.author,b.content,b.publish_time,b.blog_cover,b.introduce,b.likes,b.views,b.comment_count,t.tag_name,c.category_name " +
            "FROM t_blogContent b INNER JOIN t_tag t ON b.tag_id = t.tag_id INNER JOIN t_category c ON b.category_id = c.category_id WHERE b.flag=0 AND t.flag=0 AND c.flag=0 ORDER BY b.publish_time desc")
    List<BlogShow> findAllBook();

    //修改博客信息
    @Update("update t_blogContent set title=#{title},author=#{author},content=#{content},introduce=#{introduce},category_id=#{categoryId},tag_id=#{tagId},blog_cover=#{blogCover} ,publish_time=#{publishTime}  where blog_id=#{blogId} and flag = 0")
    void updateById(BlogContent blogContent);

    //模糊查询
    @Select("SELECT b.blog_id,b.title,b.author,b.content,b.publish_time,b.blog_cover,b.introduce,b.likes,b.views,b.comment_count,t.tag_name,c.category_name " +
            "FROM t_blogContent b INNER JOIN t_tag t ON b.tag_id = t.tag_id INNER JOIN t_category c ON b.category_id = c.category_id " +
            "WHERE b.flag=0 AND t.flag=0 AND c.flag=0 and b.title LIKE CONCAT('%',#{param1},'%') ORDER BY b.publish_time desc")
    List<BlogShow> findlikeBook(String title);

    //增加评论数
    @Update("update t_blogContent set comment_count = comment_count+1 where blog_id=#{param1} and flag = 0")
    void updateComcount(int blogId);

    @Select("select blog_id from t_blogContent where category_id = #{param1} and flag = 0")
    List<BlogContent> findAllByCategoryId(int categoryId);

    @Select("select blog_id from t_blogContent where tag_id = #{param1} and flag = 0")
    List<BlogContent> findAllByTagId(int tagId);

    @Select("SELECT DATE_FORMAT(publish_time,'%Y-%m') as date,COUNT(*) as count, category_id from t_blogContent WHERE flag = 0 and category_id = #{param1} group by date order by date desc")
    List<BlogCategory> showArchiveByCategoryId(int categoryId);

    @Select("SELECT blog_id,title,publish_time FROM t_blogContent WHERE category_id = #{param1} and flag = 0 and publish_time > #{param2} and publish_time < #{param3} order by publish_time desc")
    List<BlogContent> getBlogsByCategories(int categoryId, Date startDate, Date endDate);

    @Select("SELECT DATE_FORMAT(publish_time,'%Y-%m') as date,COUNT(*) as count, tag_id from t_blogContent WHERE flag = 0 and tag_id = #{param1} group by date order by date desc")
    List<BlogTag> showArchiveByTagId(int tagId);

    @Select("SELECT blog_id,title,publish_time FROM t_blogContent WHERE tag_id = #{param1} and flag = 0 and publish_time > #{param2} and publish_time < #{param3} order by publish_time desc")
    List<BlogContent> getBlogByTags(int tagId, Date startDate, Date endDate);

    @Update("update t_blogContent set comment_count = comment_count - 1 where blog_id=#{param1} and flag = 0")
    void reduceCommentCount(Integer blogId);

    @Select("select blog_id from t_blogContent where title = #{param1} and flag = 0")
    BlogShow queryBlogByTitle(String title);

    @Update("update t_blogContent set views = #{param1} where blog_id=#{param2}")
    void updateViewsById(Integer views, Integer blogId);

    @Update("update t_blogContent set likes = #{param1} where blog_id=#{param2} and flag = 0")
    void updateLikes(Integer likeNums, Integer blogId);
}
