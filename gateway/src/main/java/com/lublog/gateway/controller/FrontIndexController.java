package com.lublog.gateway.controller;

import com.lublog.constant.SysConstant;
import com.lublog.po.Statistics;
import com.lublog.po.User;
import com.lublog.service.BlogService;
import com.lublog.service.CommentService;
import com.lublog.service.StatisticsService;
import com.lublog.service.UserService;
import com.lublog.utils.IPUtils;
import com.lublog.utils.MapCache;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Types;
import java.util.*;

/**
 * @Description: java类作用描述BookController
 * @Author: lxy
 * @time: 2020/4/12 22:49
 */
@SuppressWarnings("all")
@Slf4j
@RestController
public class FrontIndexController {
    protected MapCache cache = MapCache.getInstance();
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "findAllBlog", method = RequestMethod.GET)
    public Map<String, Object> findAllBlog(HttpServletRequest request, Integer page, Integer totalPage, BlogShow findBlogContent, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = (User) request.getAttribute(SysConstant.CURRENT_USER);
        log.info("user is {}", user);
        request.setAttribute("page", page);
        page = (int) request.getAttribute("page");
        totalPage = (int)blogService.findBlogTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        List<BlogShow> blogContents = blogService.findAllByIndex(index, 12);
        BlogShow blogContent = blogService.findAllById(findBlogContent);
        log.info("totalPage = {}, page = {}, books = {}", totalPage, page, blogContents.toString());
        result.put("blogContents", blogContents);
        result.put("totalPage", totalPage);
        visitCount(request);
        return result;
    }

    private void visitCount(HttpServletRequest request) {
        // 来访ip
        String val = IPUtils.getIpAddrByRequest(request);
        Integer times = statisticsService.queryStatistics(1).getPageViewNum();
        Statistics statistics = new Statistics();
        statistics.setPageViewNum(times + 1);
        // 缓存中是否存在
        Integer count = cache.hget(SysConstant.VISIT_COUNT, val);
        if (null != count && count > 0) {
            log.info("不记录访问次数, count = {}", count);
            // 存在，不记录访问次数
        } else {
            // 存入缓存中，并设置超时时间
            cache.hset(SysConstant.VISIT_COUNT, val, 1, SysConstant.VISIT_COUNT_TIME);
            // 入库
            statisticsService.updateStatisticsNum(statistics, 1);
            log.info("入库");
        }
    }


}
