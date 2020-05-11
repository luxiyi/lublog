package com.lublog.provider.dao;

import com.lublog.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: BorrowMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:10
 */
public interface BorrowMapper {
    //展现借的订单信息
    @Select("select * from orderbooksbor WHERE luser =#{param1}")
    List<Order> findAllOrder(String luser);
    //插入借订单消息
    @Insert("insert into orderbooksbor (luser, oname, ocount, bid) values (#{param1}, #{param2}, #{param3}, #{param4})")
    void addOrder(String luser, String oname, int ocount, int bid);
}
