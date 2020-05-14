package com.lublog.provider.dao;

import com.lublog.po.Orderre;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: ReplayMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/4/7 1:10
 */
public interface ReplayMapper {
    //展现还的订单信息
    @Select("select * from orderbooksre WHERE luser =#{param1}")
    public List<Orderre> findAllOrderre(String luser);
    //插入还订单消息
    @Insert("insert into orderbooksre (luser, oname, ocount, bid) values (#{param1}, #{param2}, #{param3}, #{param4})")
    public void addOrderre(String luser, String oname, int ocount, int bid);
}
