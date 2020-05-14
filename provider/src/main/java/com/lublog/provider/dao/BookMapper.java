package com.lublog.provider.dao;

import com.lublog.po.BlogContent;
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
public interface BookMapper {
    //根据页码查找全部书
    @Select("SELECT blogid,title,author,price,bcount,pubdate,press,blogcover,introduce,flag,commentcount,likes FROM blogContent limit #{param1},#{param2}")
    List<BlogContent> findALLByIndex(int index, int count);
    //查询总条数
    @Select("SELECT COUNT(*) total FROM blogContent")
    int findTotalPage();
    //增加新书
    @Insert("insert into blogContent (title,author,price,bcount,pubdate,press,blogcover,introduce) values (#{title},#{author},#{price},#{bcount},#{pubdate},#{press},#{blogcover},#{introduce})")
    void insertBook(BlogContent blogContent);
    //根据blogid查找书
    @Select("select * from blogContent where blogid=#{blogid}")
    BlogContent findAllById(BlogContent blogContent);
    @Select("select * from blogContent where blogid=#{blogid}")
    BlogContent findBookById(int blogId);
    //根据博客标题判断是否存在
    @Select("select title from blogContent where title=#{title}")
    BlogContent findSameTitle(String newTitle);
    //删除书
    @Delete("delete from blogContent where blogid = #{blogid}")
    void deletById(int blogId);
    //查询全部的书
    @Select("select * from blogContent")
    List<BlogContent> findAllBook();
    //修改书信息
    @Update("update blogContent set title=#{title},author=#{author},press=#{press},introduce=#{introduce},bcount=#{bcount},price=#{price},blogcover=#{blogcover} ,pubdate=#{pubdate}  where blogid=#{blogid}")
    void updateById(BlogContent blogContent);
    //模糊查询
    @Select("SELECT * FROM blogContent WHERE title LIKE CONCAT('%',#{param1},'%')")
    List<BlogContent> findlikeBook(String title);
    //增加评论数
    @Update("update blogContent set commentcount = commentcount+1 where blogid=#{param1}")
    void updateComcount(int blogId);
}
