package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
public class ArticleEditController {
    @Autowired
    private BlogService blogService;

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
