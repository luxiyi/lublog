package com.lublog.gateway.controller.admin;

import com.lublog.service.CommentService;
import com.lublog.vo.BlogShow;
import com.lublog.vo.CommentShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述CommetController
 * @Author: lxy
 * @time: 2020/7/17 0:23
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class CommetController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/showAllComments", method = RequestMethod.GET)
    public Map<String, Object> showAllComments(HttpServletRequest request, Integer page, Integer totalPage) {
        Map<String, Object> result = new HashMap<String, Object>();
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        // 获取页码、总页码
        page = (Integer) request.getAttribute("page");
        totalPage = commentService.findTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        List<CommentShow> commentShows = commentService.findAllByIndex(index, 12);
        log.info("totalPage = {}, page = {}, comments = {}", totalPage, page, commentShows.toString());
        // 将所有书放入session
        request.setAttribute("commentShows", commentShows);
        result.put("commentShows", commentShows);
        result.put("totalPage", totalPage);
        return result;
    }
}
