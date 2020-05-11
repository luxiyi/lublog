package com.lublog.service;

import com.lublog.pojo.Order;

import java.util.List;

/**
 * @Description: java类作用描述BorrowService
 * @Author: lxy
 * @time: 2020/4/6 21:59
 */
public interface BorrowService {
    List<Order> findAllOrder(String luser);
    void addOrder(String luser, String oname, int ocount, int bid);
}
