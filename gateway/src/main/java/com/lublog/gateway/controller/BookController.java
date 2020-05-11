package com.lublog.gateway.controller;

import com.lublog.pojo.Book;
import com.lublog.pojo.Cart;
import com.lublog.pojo.LoginUser;
import com.lublog.service.BookService;
import com.lublog.service.CartService;
import com.lublog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述BookController
 * @Author: lxy
 * @time: 2020/4/12 22:49
 */
@SuppressWarnings("all")
@Controller
public class BookController {
    private Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @RequestMapping("allBooks")
    @ResponseBody
    public Map<String, Object> allBooks(HttpSession session, Integer page, Integer totalPage, Book book, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Book> books = new ArrayList<>();

        session.setAttribute("page", page);
        session.setAttribute("totalPage", totalPage);
        // 获取页码、总页码
        page = (int) session.getAttribute("page");
        totalPage = bookService.findTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        books = bookService.findAllByIndex(index, 12);
        book = bookService.findAllById(book);
        LOG.info("totalPage = {}, page = {}, books = {}", totalPage, page, books.toString());
        // 将所有书放入session
        session.setAttribute("books", books);
        session.setAttribute("book", book);
        session.setAttribute("bbid", bbid);
        result.put("books", books);
        result.put("totalPage", totalPage);
        return result;
    }

    // 传入参数并跳转页面
    @RequestMapping("sendDatil")
    public ModelAndView sendDatil(Book book) {
        ModelAndView mav = new ModelAndView();
        // 要跳转的页面
        mav.setViewName("/detail");
        // 传入对象
        mav.addObject("book", book);
        LOG.info("sendDetail，bookid = {}", book.getBid());
        return mav;

    }


    // 采用超链接跳转传参到商品详情页面，最主要是获取传过去对象bid的值
    @RequestMapping("findOneBook")
    @ResponseBody
    public Book findOneBook(Book book, HttpSession session, HttpServletRequest request) {
        LOG.info("------findOneBook------");
        int bid = Integer.parseInt(request.getParameter("bid"));
        book = bookService.findBookById(bid);
        return book;
    }

    // 搜索商品
    @RequestMapping("likeBook")
    @ResponseBody
    public List<Book> likeBook(HttpSession session, String lbname) {
        session.setAttribute("lbname", lbname);
        List<Book> books = new ArrayList<>();
        books = bookService.findlikeBook(lbname);
        LOG.info("likeBook, bookname = {}", lbname);
        return books;
    }

    @RequestMapping("addBooks")
    @ResponseBody
    public String addBooks(LoginUser user, Book book, HttpSession session, String info) {
        // 判断非空
        user = (LoginUser) session.getAttribute("user");
        LoginUser rootuser = userService.findRootByluser(user);
        if (rootuser == null) {
            info = "你不是管理员，无法操作";
            LOG.info("you are not rootuser, can't add books");
            return info;
        }
        if (null == book.getBname() || book.getBcount() == 0 || null == book.getImg() || book.getPrice() == 0
                || null == book.getAuthor() || null == book.getPress()) {
            LOG.error("add books failed, some fileds is null");
            info = "添加图书失败，字段不能为空";
            return info;
        } else {
            Book isexit = bookService.findAllById(book);// 判断是否存在
            if (isexit == null) {
                bookService.insertBook(book);
                LOG.info("add books success");
                info = "添加成功";
                return info;
            } else {
                LOG.error("add books failed, some fileds is error");
                info = "添加图书失败，请输入正确字段";
                return info;
            }
        }

    }

    // 通过找到bid删除一本书籍
    @RequestMapping("deleteBooks")
    @ResponseBody
    public String deleteBooks(HttpSession session, Book book, String info) {
        Object obj = session.getAttribute("user");
        if (obj == null) {
            info = "请先登录";
            LOG.error("you are not login");
            return info;
        } else {
            LoginUser rootuser = userService.findRootByluser((LoginUser) obj);
            if (rootuser == null) {
                info = "你不是管理员，无法操作";
                LOG.error("you are not rootuser");
                return info;
            } else {
                LOG.info("bookid = {}", book.getBid());
                book = bookService.findAllById(book);
//                Cart cart = cartService.findCarByBid(book);
                int cartBookNum = cartService.findBookInCartNumByid(book.getBid());
                LOG.info("数量 = {}",cartBookNum);
                //TODO 还有异常判断 小于0
                if (cartBookNum > 1 ) {
                    //TODO 已被点赞是否还要删除
                    LOG.info("该博客已被点赞");
                    info = "该博客已被点赞,不能被删除";
                    return info;
                }
                cartService.decartById(book.getBid());
                bookService.deleteById(book);
                info = "删除成功";
                return info;
            }
        }
    }

    // 修改书籍首先找到书籍的内容
    @RequestMapping("queryBooks")
    @ResponseBody
    public Map<String, Object> queryBooks(HttpSession session, String info, Book book) {
        Map<String, Object> result = new HashMap<String, Object>();
        Object obj = session.getAttribute("user");
        if (obj == null) {
            info = "请先登录";
            result.put("info", info);
            LOG.info("you are not login");
            return result;
        } else {
            LoginUser rootuser = userService.findRootByluser((LoginUser) obj);
            if (rootuser == null) {
                LOG.info("you are not rootuser, can't modify book");
                info = "你不是管理员，无法操作";
                result.put("info", info);
                return result;
            } else {
                LOG.info("bookid = {}", book.getBid());
                book= bookService.findAllById(book);
                result.put("book", book);
                return result;
            }
        }
    }

    // 修改书籍内容
    @RequestMapping("updateBooks")
    @ResponseBody
    public String updateBooks(Book book, String info) {
        if (book == null) {
            LOG.error("modify book failed");
            info = "更新图书失败，请重新输入字段";
            return info;
        }
        // 通过传过来的bid判断需要更新的书籍是否存在
        Book isexit = bookService.findAllById(book);
        // 不存在新的书，就更新
        if (isexit != null) {
            bookService.updateById(book);
            info = "修改成功";
            return info;
        } else {
            info = "更新图书失败，请重新输入字段";
            return info;
        }
    }

    @RequestMapping(value = "/detail")
    public String bookDetail() {
        LOG.info("-------------books detail-------------");
        return "detail";
    }

}
