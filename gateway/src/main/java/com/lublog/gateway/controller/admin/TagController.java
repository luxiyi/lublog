package com.lublog.gateway.controller.admin;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lublog.po.BlogContent;
import com.lublog.po.Category;
import com.lublog.po.Tag;
import com.lublog.service.BlogService;
import com.lublog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 标签
 * @Author: lxy
 * @time: 2020/5/26 0:21
 */
@RestController
@Slf4j
public class TagController {
    private int tagNameLimitLength = 8;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    /**
     * 加载所有的博客标签
     *
     * @return tagJsonString
     */
    @RequestMapping(value = "/listBlogTag", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String listBlogTag() {
        List<Tag> tags = tagService.findAllTags();
        log.info("tagList.size is {}", tags.size());
        String tagJsonString = JSON.toJSONString(tags, SerializerFeature.DisableCircularReferenceDetect);
        log.info("tagJsonString is {}", tagJsonString);
        return tagJsonString;
    }

    @PostMapping(value = "/admin/addTag")
    public String addTag(@RequestParam("tagName") String tagName) {
        String msg = "添加 " + tagName + " 标签成功";
        if (StringUtils.isEmpty(tagName) || tagName == "") {
            msg = "添加的标签为空，请输入需要添加的标签";
            log.error("add tagName is null, please input categoryName again");
            return msg;
        }
        if (tagName.length() > tagNameLimitLength) {
            msg = "添加的标签名超过限制长度，请重新输入需要添加的标签";
            log.error("add tagName.length {} is logger than Limited length, please input tagName again", tagName.length());
            return msg;
        }
        Tag findTag = tagService.findOneTagByName(tagName);
        if (null != findTag) {
            msg = tagName + " 标签已存在，请重新输入需要添加的标签";
            log.error("add tagName has existed, please input tagName again");
            return msg;
        }
        try {
            tagService.addTag(tagName);
        } catch (Exception e) {
            msg = "添加 " + tagName + " 标签失败";
            log.error("add {} failed ,{}", tagName, e);
            return msg;
        }
        return msg;
    }

    @PostMapping(value = "/admin/deleteTag")
    public Map<String, Object> deleteTag(@RequestParam("tagName") String tagName) {
        String msg = "删除 " + tagName + " 标签成功";
        Map<String, Object> result = new HashMap<>();
        Tag currentTag;
        try {
            currentTag = tagService.findOneTagByName(tagName);
            result.put("currentTag", currentTag);
        } catch (Exception e) {
            msg = "删除 " + tagName + " 标签失败";
            log.error("find blogContent by tagId error is {}", e);
            result.put("msg", msg);
            return result;
        }
        currentTag = tagService.findOneTagByName(tagName);
        if (currentTag == null) {
            msg = "删除 " + tagName + " 标签失败";
            log.error("find blogContent by tagId error");
            result.put("msg", msg);
            return result;
        }
        List<BlogContent> blogContents = blogService.findAllByTagId(currentTag.getTagId());
        if (CollectionUtils.isNotEmpty(blogContents)) {
            msg = tagName + " 标签有博客使用，无法删除";
            result.put("msg", msg);
            log.error("delete {} failed, this categoryName is used by blogContent", tagName);
            return result;
        }
        try {
            tagService.deleteTagByName(tagName);
        } catch (Exception e) {
            msg = "删除 " + tagName + " 标签失败";
            log.error("delete {} failed,{}", tagName, e);
            result.put("msg", msg);
            return result;
        }
        result.put("msg", msg);
        return result;
    }

}
