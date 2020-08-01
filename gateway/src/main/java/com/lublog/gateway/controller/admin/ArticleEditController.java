package com.lublog.gateway.controller.admin;

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
import java.nio.file.Files;
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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){
        JSONObject jsonObject=new JSONObject();

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
            File realFile = new File(rootPath + File.separator + attach.getOriginalFilename());
            Files.copy(attach.getInputStream(),realFile.toPath());
            //FileUtils.copyInputStreamToFile(attach.getInputStream(), realFile);

            // 下面response返回的json格式是editor.md所限制的，规范输出就OK
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", "/admin/img/"+attach.getOriginalFilename());
        } catch (Exception e) {
            jsonObject.put("success", 0);
        }
        return jsonObject.toString();
    }
}
