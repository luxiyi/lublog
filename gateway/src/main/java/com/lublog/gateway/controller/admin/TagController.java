package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lublog.po.BlogContent;
import com.lublog.po.Tag;
import com.lublog.service.BlogService;
import com.lublog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 标签
 * @Author: lxy
 * @time: 2020/5/26 0:21
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    /**
     * 加载所有的博客类别
     * @return
     */
    @RequestMapping(value = "/listArticleTag" ,method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String listArticleTag() {
        List<Tag> tags = tagService.findAllTags();
        log.info("tagList.size is {}",tags.size());
        String tagJsonString = JSON.toJSONString(tags, SerializerFeature.DisableCircularReferenceDetect);
        return tagJsonString;
    }
}
