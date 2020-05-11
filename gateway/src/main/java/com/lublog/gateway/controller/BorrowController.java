package com.lublog.gateway.controller;

import com.lublog.pojo.LoginUser;
import com.lublog.pojo.Order;
import com.lublog.service.BorrowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: java类作用描述BorrowController
 * @Author: lxy
 * @time: 2020/4/16 0:47
 */
@SuppressWarnings("all")
@Controller
public class BorrowController {
    private Logger LOG = LoggerFactory.getLogger(BorrowController.class);
    @Autowired
    private BorrowService borrowService;

    @RequestMapping(value = "/order")
    public String order() {
        LOG.info("----------book order----------");
        return "order";
    }
    @RequestMapping(value = "/showOrder")
    @ResponseBody
    public List<Order> showOrder(HttpSession session){
        LoginUser user = (LoginUser) session.getAttribute("user");
        List<Order> borrows = borrowService.findAllOrder(user.getLuser());
        LOG.info("book showOrder");
        return borrows;
    }

}
