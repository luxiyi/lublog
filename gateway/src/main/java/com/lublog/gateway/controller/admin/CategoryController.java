package com.lublog.gateway.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lublog.po.Category;
import com.lublog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 分类
 * @Author: lxy
 * @time: 2020/5/26 1:41
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/listArticleCategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String listArticleCategory() {
        List<Category> categories = categoryService.findAllCategories();
        String tagJsonString = JSON.toJSONString(categories, SerializerFeature.DisableCircularReferenceDetect);
        return tagJsonString;
    }
}
