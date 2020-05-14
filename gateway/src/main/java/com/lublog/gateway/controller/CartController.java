package com.lublog.gateway.controller;

import com.lublog.po.BlogContent;
import com.lublog.po.BookCart;
import com.lublog.vo.Cart;
import com.lublog.po.LoginUser;
import com.lublog.service.BookService;
import com.lublog.service.BorrowService;
import com.lublog.service.CartService;
import com.lublog.service.ReplayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description: java类作用描述CartController
 * @Author: lxy
 * @time: 2020/4/16 0:46
 */
@SuppressWarnings("all")
@Controller
public class CartController {
    private Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private ReplayService replayService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/cart")
    public String Cart(HttpSession session) {
            logger.info("-------cart------");
            return "cart";
    }

    // 通过用户id查找查询书架中所有书
    @RequestMapping(value = "allCarts")
    @ResponseBody
    public Map<String, Object> allCarts(HttpSession session, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        LoginUser user = (LoginUser) session.getAttribute("user");
        // 判断是否登录
        if (user == null) {
            // 没有登录，创建HashMap
            msg = "请先登录";
            result.put("msg", msg);
            return result;
        } else {
            List<Cart> carts = cartService.allCartByLuser(user);
            result.put("carts", carts);
            System.out.println(carts);
            return result;
        }
    }

    // 添加书到书架中,用map方法 直接添加到视图中是不行的,借书
    @RequestMapping(value = "/addCart")
    @ResponseBody
    public Map<String, Object> addCart(HttpSession session, BlogContent blogContent, LoginUser user, Cart cart, BookCart bookCart) {
        Map<String, Object> reslut = new HashMap<>();
        String msg = "请联系帅气的管理员";
                // 判断操作类型,新增还是修改
        // 通过网页中传过来的bid查看书架中是否有书
        user = (LoginUser) session.getAttribute("user");
        if (user == null) {
            // 没有登录，创建HashMap
            msg = "请先登录";
            reslut.put("msg", msg);
            return reslut;
        }
        logger.info("cart = {}", cart.toString());
        int count = cartService.bookcountByBid(cart.getBid());

        // 如果图书量数量大于0
        if (count > 0) {
            // 怎么添加到书架中
            cart = cartService.oneCartBybid(user.getLuser(), cart.getBid());
            if (cart == null) {
                // 新增一个书数据
                cartService.addCart(user.getLuser(), bookCart.getBid(), bookCart.getCcount());
                cartService.reBookCount(bookCart.getCcount(), bookCart.getBid());
                // 添加订单信息,插入订单信息UUID 订单号必须唯一 随机产生单号
                Random random = new Random();
                String oname = "" + System.currentTimeMillis() + user.getLuser() + random.nextInt(99);
                borrowService.addOrder(user.getLuser(), oname, bookCart.getCcount(), bookCart.getBid());
                msg = "谢谢点赞";
                reslut.put("msg", msg);
                reslut.put("bookCart", bookCart);
                logger.info("已成功点赞");
                return reslut;
            }
            if (cart.getBid() == bookCart.getBid()) {
                // 在原有的商品数据修改上进行数量的修改
                if (cart.getCount() > 0) {
                    cartService.upCartCount(user.getLuser(), bookCart.getBid());
                    cartService.reBookCount(bookCart.getCcount(), bookCart.getBid());
                    // 添加订单信息,插入订单信息UUID 订单号必须唯一 随机产生单号
                    Random random = new Random();
                    String oname = "" + System.currentTimeMillis() + user.getLuser() + random.nextInt(99);
                    borrowService.addOrder(user.getLuser(), oname, bookCart.getCcount(), bookCart.getBid());
                    msg = "已成功点赞";
                    reslut.put("msg", msg);
                    reslut.put("bookCart", bookCart);
                    logger.info("已成功点赞");
                    return reslut;
                }
            }
        } else {
            //TODO  点过赞就不需要点赞了
            logger.info("已经点过赞了哦");
            msg = "已经点过赞了哦";
            reslut.put("msg", msg);
            return reslut;
        }
        reslut.put("msg", msg);
        return reslut;
    }

    // 书架中减少图书，到最后为0删除图书，还书
    @RequestMapping("reCartBook")
    @ResponseBody
    public String reCartBook(HttpSession session, BlogContent blogContent, BookCart bookCart) {
        LoginUser user = (LoginUser) session.getAttribute("user");
        String msg = "请联系帅气的管理员";
        int bid = blogContent.getBlogid();
        int ccount = bookCart.getCcount();
        int count = cartService.cartccountByBid(user.getLuser(), bid);
        // 如果书架中书数量大于0
        if (count > 1) {
            // 书架减少数量1
            cartService.recartcountById(bid);
            // 图书总数量增加1
            cartService.addbookcountById(bid);
            // 添加订单信息,插入订单信息UUID 订单号必须唯一 随机产生单号
            Random random = new Random();
            String oname = "" + System.currentTimeMillis() + user.getLuser() + random.nextInt(99);
            replayService.addOrderre(user.getLuser(), oname, ccount, bid);
            msg = "已成功还书1本";
            return msg;
        }
        if (count == 1 || count < 1) {
            // 图书总数量增加1
            cartService.addbookcountById(bookCart.getBid());
            cartService.decartById(bookCart.getBid());
            // 添加订单信息,插入订单信息UUID 订单号必须唯一 随机产生单号
            Random random = new Random();
            String oname = "" + System.currentTimeMillis() + user.getLuser() + random.nextInt(99);
            replayService.addOrderre(user.getLuser(), oname, bookCart.getCcount(), bookCart.getBid());
            System.out.println("还完-----------------");
            msg = "已成功将此书还完";
            return msg;
        }
        return "success";
    }
}
