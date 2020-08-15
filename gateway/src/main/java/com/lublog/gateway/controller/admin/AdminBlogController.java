package com.lublog.gateway.controller.admin;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.service.BlogService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述BlogListController
 * @Author: lxy
 * @time: 2020/7/17 0:06
 */

@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminBlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 文章管理，对博客进行修改、删除
     *
     * @param request
     * @param page
     * @param findBlogContent
     * @return result 博客列表内容blogContents 页数totalPage
     */
    @RequestMapping(value = "/showAdminAllBlogs", method = RequestMethod.GET)
    public Map<String, Object> showAdminAllBlogs(HttpServletRequest request, @RequestParam("page") Integer page, BlogShow findBlogContent) {
        Map<String, Object> result = new HashMap<String, Object>();
        request.setAttribute("page", page);
        // 获取页码、总页码
        page = (Integer) request.getAttribute("page");
        Integer totalPage = blogService.findBlogTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        List<BlogShow> blogShowList = blogService.findAllByIndex(index, 12);
        BlogShow blogShow = blogService.findAllById(findBlogContent);
        log.info("totalPage = {}, page = {}, blogShowList = {}", totalPage, page, blogShowList.toString());
        // 将所有书放入session
        request.setAttribute("blogShowList", blogShowList);
        request.setAttribute("blogShow", blogShow);
        result.put("blogShowList", blogShowList);
        result.put("totalPage", totalPage);
        return result;
    }

    @RequestMapping(value = "/deleteBlog", method = RequestMethod.DELETE)
    public String deleteBlog(@RequestParam("blogId") String blogIdStr, @RequestParam("blogCount") Integer blogCount) {
        String msg = "删除成功";
        if (StringUtils.isEmpty(blogIdStr) || blogIdStr == "" || blogCount == null) {
            log.error("delete blog fail, blogIdStr is {}, blogCount is {}", blogIdStr, blogCount);
            msg = "删除失败";
            return msg;
        }
        int blogId = Integer.parseInt(blogIdStr);
        BlogShow blogShow = blogService.findBlogById(blogId);
        if (blogShow == null) {
            log.error("delete blog fail, blogShow is {}", blogShow);
            msg = "删除失败";
            return msg;
        }
        //如果有评论，则先删除评论后，再删文章
        if (blogShow.getCommentcount() > 0) {
            log.error("delete blog fail, blogShow.commentCount is {}", blogShow.getCommentcount());
            msg = "删除失败，该文章已被评论，请先删除评论";
            return msg;
        }
        try {
            blogService.deleteById(blogId);
        } catch (Exception e) {
            msg = "系统出错，请联系帅气的管理员";
            log.error("delete blog fail, MySql is error, {}", e);
            return msg;
        }
        log.info("delete blog success, blogId is {}", blogId);
        return msg;
    }

    @RequestMapping(value = "/showUpdateBlog", method = RequestMethod.GET)
    public BlogShow queryBlog(@RequestParam("blogId") Integer blogIdStr) {
//        if (StringUtils.isEmpty(blogIdStr) || blogIdStr == "") {
//            log.error("query blog fail, blogIdStr is {}", blogIdStr);
//            msg = "查询失败";
//            result.put("msg", msg);
//            return result;
//        }
        BlogShow blogShow = blogService.findBlogById(blogIdStr);
        return blogShow;
    }
}
