package com.lublog.service;

import com.lublog.po.BlogContent;
import com.lublog.vo.Cart;
import com.lublog.po.LoginUser;

import java.util.List;

/**
 * @Description: CartServicejava类作用描述
 * @Author: lxy
 * @time: 2020/4/6 21:56
 */
public interface CartService {
   List<Cart> allCartByLuser(LoginUser loginUser);
   Cart oneCartBybid(String luser, int bid);
   void addCart(String luser, int bid, int ccount);
   void updateCart(LoginUser loginUser);
   Cart findCarByBid(BlogContent blogContent);
   void upCartCount(String luser, int bid);
   void reBookCount(int count, int bid);
   int bookcountByBid(int bid);
   int cartccountByBid(String luser, int bid);
   void recartcountById(int bid);
   void decartById(int bid);
   void addbookcountById(int bid);
   int findBookInCartNumByid(int bid);

}
