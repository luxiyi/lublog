package com.lublog.provider.dao;

import com.lublog.pojo.Book;
import com.lublog.pojo.Cart;
import com.lublog.pojo.LoginUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description: java类作用描述CartMapper
 * @Author: lxy
 * @time: 2020/4/7 1:10
 */
public interface CartMapper {
    //查找书架vi_cart中所有数据
    @Select("select * from vi_cart where luser=#{luser}")
    List<Cart> allCartByLuser(LoginUser loginUser);
    //查找书架中一本书的内容
    @Select("select * from vi_cart where luser=#{param1} and bid=#{param2}")
    Cart oneCartBybid(String luser, int bid);
    //插入bookcart数据，用户id，商品id，数量
    @Insert("insert into bookcart (luser,bid,ccount) values (#{param1},#{param2},#{param3})")
    void addCart(String luser, int bid, int ccount);

    //如果登录后留下的就删除数据库中的购物车数据  其实就是更新cartservice
    @Update("update bookcart set flag=1 where luser=#{luser} and flag=0")
    void updateCart(LoginUser loginUser);
    //通过bid查看书架中是否有这个bid书
    @Select("select * from vi_cart where bid=#{bid}")
    Cart findCarByBid(Book book);
    @Select("select count(bid) from bookcart where bid = #{bid}")
    int findBookInCartNumByid(int bid);
    //添加书架图书数量
    @Update("update bookcart set ccount=ccount+1 where luser=#{param1} and bid=#{param2}")
    void upCartCount(String luser, int bid);
    //减少图书总数量
    @Update("update books set bcount=bcount-#{param1} where bid=#{param2}")
    void reBookCount(int count, int bid);
    //根据bookcart的中的bid找到图书的库存
    @Select("select bcount from books where bid=#{param1}")
    int bookcountByBid(int bid);
    //根据bookcart的中的bid找到该图书的书架数量
    @Select("select ccount from bookcart where luser=#{param1} and bid=#{param2}")
    int cartccountByBid(String luser, int bid);
    //减少书架图书中数量
    @Update("update bookcart set ccount=ccount-1 where bid=#{param1}")
    void recartcountById(int bid);
    //删除书架图书
    @Delete("delete from bookcart where ccount=1 and bid=#{param1}")
    void decartById(int bid);
    //增加图书总数量
    @Update("update books set bcount=bcount+1 where bid=#{param1}")
    void addbookcountById(int bid);
}
