package com.lublog.gateway.controller.admin;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lublog.po.BlogContent;
import com.lublog.po.Category;
import com.lublog.service.BlogService;
import com.lublog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 分类
 * @Author: lxy
 * @time: 2020/5/26 1:41
 */
@RestController
@Slf4j
public class CategoryController {
    private int tagNameLimitLength = 11;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    /**
     * 加载所有的博客类别，没有添加ResponseBody，categoryJsonString is [null,null]
     *
     * @return tagJsonString
     */
    @RequestMapping(value = "/listBlogCategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String listBlogCategory(HttpServletRequest request) {
        List<Category> categories = categoryService.findAllCategories();
        log.info("categories.size is {}", categories.size());
        String categoryJsonString = JSON.toJSONString(categories, SerializerFeature.DisableCircularReferenceDetect);
        log.info("categoryJsonString is {}", categoryJsonString);
        request.setAttribute("categories", categories);
        return categoryJsonString;
    }

    /**
     * todo 1、代码健壮性 2、字段为空、非法字符、超过长度、
     * @param categoryName
     * @return
     */
    @PostMapping(value = "/admin/addCategory")
    public String addCategory(@RequestParam("categoryName") String categoryName) {
        String msg = "添加 " + categoryName + " 分类成功";
        if (StringUtils.isEmpty(categoryName) || categoryName == "") {
            msg = "添加的分类为空，请输入需要添加的分类";
            log.error("add categoryName is null, please input categoryName again");
            return msg;
        }
        if (categoryName.length() > tagNameLimitLength) {
            msg = "添加的分类名超过限制长度，请重新输入需要添加的分类";
            log.error("add categoryName.length {} is logger than Limited length, please input categoryName again", categoryName.length());
            return msg;
        }
        //非法字符在前端判断
        Category findCategory = categoryService.findOneCategoryByName(categoryName);
        if (null != findCategory) {
            msg = categoryName + " 分类已存在，请重新输入需要添加的分类";
            log.error("add categoryName has existed, please input categoryName again");
            return msg;
        }

        try {
            categoryService.addCategory(categoryName);
        } catch (Exception e) {
            msg = "添加 " + categoryName + " 分类失败";
            log.error("add {} failed ,{}", categoryName, e);
            return msg;
        }
        return msg;
    }

    @PostMapping(value = "/admin/deleteCategory")
    public Map<String, Object> deleteCategory(@RequestParam("categoryName") String categoryName) {
        Map<String, Object> result = new HashMap<>();
        Category currentCategory = categoryService.findOneCategoryByName(categoryName);
        result.put("currentCategory", currentCategory);
        String msg = "删除 " + categoryName + " 分类成功";
        try {
            List<BlogContent> blogContents = blogService.findAllByCategoryId(currentCategory.getCategoryid());
            if (CollectionUtils.isNotEmpty(blogContents)) {
                msg = categoryName + " 分类有博客使用，无法删除";
                result.put("msg", msg);
                log.error("delete {} failed, this categoryName is used by blogContent", categoryName);
                return result;
            }
        } catch (Exception e) {
            log.error("find blogContent by categoryId error is {}", e);
        }

        try {
            categoryService.deleteCategoryByName(categoryName);
            log.info("delete {} success", currentCategory.getCategoryname());
        } catch (Exception e) {
            msg = "删除 " + currentCategory.getCategoryname() + " 分类失败";
            log.error("delete {} failed,{}", currentCategory.getCategoryname(), e);
            result.put("msg", msg);
            return result;
        }
        result.put("msg", msg);
        return result;
    }


}
