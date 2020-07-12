package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 博客编辑操作
 * @Author: lxy
 * @time: 2020/5/23 14:39
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class ArticleController {
    @Autowired
    private BlogService blogService;

    /**
     *
     * 文章管理，对博客进行修改、删除
     * @param request
     * @param page
     * @param totalPage
     * @param findBlogContent
     * @param bbid
     * @return result 博客列表内容blogContents 页数totalPage
     */
    @RequestMapping(value = "/showAllArticles", method = RequestMethod.GET)
    public Map<String, Object> showAllArticles(HttpServletRequest request, Integer page, Integer totalPage, BlogShow findBlogContent, String bbid) {
        Map<String, Object> result = new HashMap<String, Object>();
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        // 获取页码、总页码
        page = (Integer) request.getAttribute("page");
        totalPage = blogService.findTotalPage();
        // 得到每一页所有书
        int index = (page - 1) * 12;
        List<BlogShow> blogShowList = blogService.findAllByIndex(index, 12);
        BlogShow blogShow = blogService.findAllById(findBlogContent);
        log.info("totalPage = {}, page = {}, books = {}", totalPage, page, blogShowList.toString());
        // 将所有书放入session
        request.setAttribute("blogContents", blogShowList);
        request.setAttribute("blogContent", blogShow);
        request.setAttribute("bbid", bbid);
        result.put("blogContents", blogShowList);
        result.put("totalPage", totalPage);
        return result;
    }


    /**
     * 保存发布文章
     */
    @RequestMapping(value = "/pushArticle", method = RequestMethod.POST)
    public String saveOrUpdateBlog(@RequestParam("title") String title, @RequestParam("content") String content,
                                   @RequestParam("categoryId") String categoryIdStr, @RequestParam("tagId") String tagIdStr,
                                   @RequestParam("author") String author,  @RequestParam("introduce") String introduce) {
        int categoryId = Integer.parseInt(categoryIdStr);
        int tagId = Integer.parseInt(tagIdStr);
        BlogContent blogContent = new BlogContent();
        blogContent.setTitle(title);
        blogContent.setContent(content);
        blogContent.setPubdate(new Date());
        blogContent.setCategoryid(categoryId);
        blogContent.setTagid(tagId);
        blogContent.setAuthor(author);
        blogContent.setIntroduce(introduce);
        JSONObject result = new JSONObject();
        try {
            this.blogService.insertBlog(blogContent);
            result.put("result", "success");
            return result.toJSONString();
        } catch (Exception e) {
            log.error("blog push failed is {}", e);
            result.put("result", "error");
            return result.toJSONString();
        }
    }
}
