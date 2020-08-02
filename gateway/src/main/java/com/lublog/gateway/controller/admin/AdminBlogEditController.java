package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @Description: 博客编辑操作
 * @Author: lxy
 * @time: 2020/5/23 14:39
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminBlogEditController {
    @Autowired
    private BlogService blogService;

    /**
     * 保存发布文章
     */
    @RequestMapping(value = "/pushBlog", method = RequestMethod.POST)
    public String pushBlog(@RequestParam("title") String title, @RequestParam("content") String content,
                                   @RequestParam("categoryId") String categoryIdStr, @RequestParam("tagId") String tagIdStr,
                                   @RequestParam("author") String author, @RequestParam("introduce") String introduce,
                                   @RequestParam("blogCover") String blogCover, HttpServletRequest request) {
        String msg = "发布成功!";
        BlogContent blogContent = new BlogContent();
        blogContent = setBlogContent(blogContent, categoryIdStr, tagIdStr, title, content, author, introduce, blogCover);
        if (blogService.queryBlogByTitle(title) != null) {
            log.error("publish is fail, blog has existed");
            msg = "标题已存在，请重新命名标题";
            return msg;
        }
        try {
            this.blogService.insertBlog(blogContent);
        } catch (Exception e) {
            log.error("blog push failed is {}", e);
            msg = "发布失败，请联系帅气的管理员";
            return msg;
        }
        return msg;
    }

    /**
     * 上传文章封面图片
     * @param request
     * @param response
     * @param blogCover
     * @return
     */
    @RequestMapping(value = "/uploadBlogCover", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadBlogCover(HttpServletRequest request, HttpServletResponse response,
                                               @RequestParam(value = "blogCover", required = false) MultipartFile blogCover) {
        Map<String, Object> reslut = new HashMap<>();
        try {
            String fileName = blogCover.getOriginalFilename();
            log.info("blogCover is {}", fileName);
            String path = request.getServletContext().getRealPath("/admin/img/");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            fileName = UUID.randomUUID().toString() + fileName;
            path = path + File.separator + fileName;
            file = new File(path);
            blogCover.transferTo(file);

            reslut.put("success", 1);
            reslut.put("message", "上传成功");
            reslut.put("url", "/admin/img/" + fileName);
        } catch (Exception e) {
            reslut.put("success", 0);
            reslut.put("message", "上传失败");
            log.error("upload file fail is {}", e);
        }
        return reslut;
    }

    /**
     * markdown图片上传
     *
     * @param request
     * @param response
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "editormd-image-file", required = false) MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        log.info("file is {}", file.getOriginalFilename());
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/admin/img");

            log.info("editormd上传图片：{}", rootPath);

            /**
             * 文件路径不存在则需要创建文件路径
             */
            File filePath = new File(rootPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            // 最终文件名
            File realFile = new File(rootPath + File.separator + file.getOriginalFilename());
            //保存
            try {
                file.transferTo(realFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", "/admin/img/" + file.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", 0);
            log.error("upload file fail is {}", e);
        }
        return jsonObject.toString();
    }




    @RequestMapping(value = "/updateBlog", method = RequestMethod.PUT)
    public String saveOrUpdateBlog(@RequestParam("title") String title, @RequestParam("content") String content,
                                   @RequestParam("categoryId") String categoryIdStr, @RequestParam("tagId") String tagIdStr,
                                   @RequestParam("author") String author, @RequestParam("introduce") String introduce,
                                   @RequestParam("blogCover") String blogCover) {
        String msg = "更新成功!";
        BlogContent blogContent = new BlogContent();
        blogContent = setBlogContent(blogContent, categoryIdStr, tagIdStr, title, content, author, introduce, blogCover);
        try {
            this.blogService.updateById(blogContent);
        } catch (Exception e) {
            log.error("blog update failed is {}", e);
            msg = "更新失败，请联系帅气的管理员";
            return msg;
        }
        return msg;
    }

    private BlogContent setBlogContent(BlogContent blogContent, String categoryIdStr, String tagIdStr, String title, String content, String author,
                                String introduce, String blogCover){
        int categoryId = Integer.parseInt(categoryIdStr);
        int tagId = Integer.parseInt(tagIdStr);
        blogContent.setTitle(title);
        blogContent.setContent(content);
        blogContent.setPubdate(new Date());
        blogContent.setCategoryid(categoryId);
        blogContent.setTagid(tagId);
        blogContent.setAuthor(author);
        blogContent.setIntroduce(introduce);
        blogContent.setBlogcover(blogCover);
        return blogContent;
    }
}
