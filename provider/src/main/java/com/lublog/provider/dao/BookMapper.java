package com.lublog.provider.dao;

import com.lublog.pojo.Book;
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
    @Select("SELECT bid,bname,author,price,bcount,pubdate,press,img,intro,flag,comcount FROM books limit #{param1},#{param2}")
    List<Book> findALLByIndex(int index, int count);
    //查询总条数
    @Select("SELECT COUNT(*) total FROM books")
    int findTotalPage();
    //增加新书
    @Insert("insert into books (bname,author,price,bcount,pubdate,press,img,intro) values (#{bname},#{author},#{price},#{bcount},#{pubdate},#{press},#{img},#{intro})")
    void insertBook(Book book);
    //根据bid查找书
    @Select("select * from books where bid=#{bid}")
    Book findAllById(Book book);
    @Select("select * from books where bid=#{bid}")
    Book findBookById(int bid);
    //删除书
    @Delete("delete from books where bid = #{bid}")
    void deletById(Book book);
    //查询全部的书
    @Select("select * from books")
    List<Book> findAllBook();
    //修改书信息
    @Update("update books set bname=#{bname},author=#{author},press=#{press},intro=#{intro},bcount=#{bcount},price=#{price},img=#{img} ,pubdate=#{pubdate}  where bid=#{bid}")
    void updateById(Book book);
    //模糊查询
    @Select("SELECT * FROM books WHERE bname LIKE CONCAT('%',#{param1},'%')")
    List<Book> findlikeBook(String bname);
    //增加评论数
    @Update("update books set comcount = comcount+1 where bid=#{param1}")
    void updateComcount(int bid);
}
