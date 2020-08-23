package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lublog.gateway.utils.getObjectUtils;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
//    public final static String IMG_PATH_PREFIX = "static/upload/img";

    @Autowired
    private BlogService blogService;

    /**
     * 保存发布文章
     */
    @RequestMapping(value = "/pushBlog", method = RequestMethod.POST)
    public String pushBlog(@RequestParam("title") String title, @RequestParam("content") String content,
                                   @RequestParam("categoryId") Integer categoryId, @RequestParam("tagId") Integer tagId,
                                   @RequestParam("author") String author, @RequestParam("introduce") String introduce,
                                   @RequestParam("blogCover") String blogCover, HttpServletRequest request) {
        String msg = "发布成功!";
        BlogContent blogContent = new BlogContent();
        blogContent = geyBlogContent(blogContent, categoryId, tagId, title, content, author, introduce, blogCover);
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
        String fileDirPath = System.getProperty("user.dir") + "/File/upload/img";
        try {
            File newFile = getNewFile(blogCover, fileDirPath);
            blogCover.transferTo(newFile.getAbsoluteFile());
            reslut.put("success", 1);
            reslut.put("message", "上传成功");
            reslut.put("url", "/File/upload/img/" + newFile.getName());
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
            String fileDirPath = System.getProperty("user.dir") + "/File/upload/img";
            File newFile = getNewFile(file, fileDirPath);
            file.transferTo(newFile.getAbsoluteFile());
            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", "/File/upload/img/" + newFile.getName());
        } catch (Exception e) {
            jsonObject.put("success", 0);
            log.error("upload file fail is {}", e);
        }
        return jsonObject.toString();
    }




    @RequestMapping(value = "/updateBlog", method = RequestMethod.PUT)
    public String updateBlog(@RequestParam("title") String title, @RequestParam("content") String content,
                                   @RequestParam("categoryId") Integer categoryId, @RequestParam("tagId") Integer tagId,
                                   @RequestParam("author") String author, @RequestParam("introduce") String introduce,
                                   @RequestParam("blogCover") String blogCover, Integer blogId) {
        String msg = "更新成功!";
        BlogContent blogContent = new BlogContent();
        blogContent = geyBlogContent(blogContent, categoryId, tagId, title, content, author, introduce, blogCover);
        blogContent.setBlogId(blogId);
        try {
            this.blogService.updateById(blogContent);
        } catch (Exception e) {
            log.error("blog update failed is {}", e);
            msg = "更新失败，请联系帅气的管理员";
            return msg;
        }
        return msg;
    }



    private File getNewFile(MultipartFile blogCover,String fileDirPath){
        log.info("fileDirPath is {}", fileDirPath);
        File fileDir = new File(fileDirPath);
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        log.info(fileDir.getAbsolutePath());
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        String fileName = getFileName(blogCover);
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + fileName);
        log.info(newFile.getAbsolutePath());
        return newFile;
    }

    private String getFileName(MultipartFile file){
        String originalFileName=file.getOriginalFilename();
        String suffix=originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + suffix;
        return fileName;
    }

    public static BlogContent geyBlogContent(BlogContent blogContent, Integer categoryId, Integer tagId, String title, String content, String author,
                                             String introduce, String blogCover){
        blogContent.setTitle(title);
        blogContent.setContent(content);
        blogContent.setPublishTime(new Date());
        blogContent.setCategoryId(categoryId);
        blogContent.setTagId(tagId);
        blogContent.setAuthor(author);
        blogContent.setIntroduce(introduce);
        blogContent.setBlogCover(blogCover);
        return blogContent;
    }
}
