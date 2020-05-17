package com.lublog.provider.dao;

import com.lublog.po.BlogContent;
import com.lublog.vo.BlogShow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description: BookMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:08
 */
public interface BlogMapper {
    //根据页码查找全部书
    @Select("SELECT b.blogid,b.title,b.author,b.content,b.pubdate,b.blogcover,b.introduce,b.likes,b.views,b.commentcount,t.tagname,c.categoryname " +
            "FROM blogcontent b INNER JOIN tag t ON b.tagid = t.tagid INNER JOIN category c ON b.categoryid = c.categoryid WHERE b.flag=0 AND t.flag=0 AND c.flag=0 " +
            "limit #{param1},#{param2}")
    List<BlogShow> findALLByIndex(int index, int count);

    //查询总条数
    @Select("SELECT COUNT(*) total FROM blogContent")
    int findTotalPage();

    //增加新书
    @Insert("insert into blogContent (title,author,pubdate,blogcover,introduce,content,typeId) values (#{title},#{author},#{pubdate},#{blogcover},#{introduce},#{content},#{typeId})")
    void insertBook(BlogContent blogContent);

    //根据blogid查找书
    @Select("SELECT b.blogid,b.title,b.author,b.content,b.pubdate,b.blogcover,b.introduce,b.likes,b.views,b.commentcount,t.tagname,c.categoryname " +
            "FROM blogcontent b INNER JOIN tag t ON b.tagid = t.tagid INNER JOIN category c ON b.categoryid = c.categoryid " +
            "where blogid=#{blogid} AND b.flag=0 AND t.flag=0 AND c.flag=0")
    BlogShow findAllById(BlogShow blogShow);

    @Select("SELECT b.blogid,b.title,b.author,b.content,b.pubdate,b.blogcover,b.introduce,b.likes,b.views,b.commentcount,t.tagname,c.categoryname " +
            "FROM blogcontent b INNER JOIN tag t ON b.tagid = t.tagid INNER JOIN category c ON b.categoryid = c.categoryid " +
            "where blogid=#{blogid} AND b.flag=0 AND t.flag=0 AND c.flag=0")
    BlogShow findBookById(int blogId);

    //根据博客标题判断是否存在
    @Select("select title from blogContent where title=#{title}")
    BlogContent findSameTitle(String newTitle);

    //删除书
    @Delete("delete from blogContent where blogid = #{blogid}")
    void deletById(int blogId);

    //查询全部的书
    @Select("SELECT b.blogid,b.title,b.author,b.content,b.pubdate,b.blogcover,b.introduce,b.likes,b.views,b.commentcount,t.tagname,c.categoryname " +
            "FROM blogcontent b INNER JOIN tag t ON b.tagid = t.tagid INNER JOIN category c ON b.categoryid = c.categoryid WHERE b.flag=0 AND t.flag=0 AND c.flag=0 ")
    List<BlogShow> findAllBook();

    //修改书信息
    @Update("update blogContent set title=#{title},author=#{author},press=#{press},introduce=#{introduce},bcount=#{bcount},price=#{price},blogcover=#{blogcover} ,pubdate=#{pubdate}  where blogid=#{blogid}")
    void updateById(BlogContent blogContent);

    //模糊查询
    @Select("SELECT b.blogid,b.title,b.author,b.content,b.pubdate,b.blogcover,b.introduce,b.likes,b.views,b.commentcount,t.tagname,c.categoryname " +
            "FROM blogcontent b INNER JOIN tag t ON b.tagid = t.tagid INNER JOIN category c ON b.categoryid = c.categoryid " +
            "WHERE title LIKE CONCAT('%',#{param1},'%')")
    List<BlogShow> findlikeBook(String title);

    //增加评论数
    @Update("update blogContent set commentcount = commentcount+1 where blogid=#{param1}")
    void updateComcount(int blogId);

}
