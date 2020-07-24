package com.lublog.service;

import com.lublog.po.Category;

import java.util.List;

/**
 * @Description: java类作用描述CategoryService
 * @Author: lxy
 * @time: 2020/5/26 1:53
 */
public interface CategoryService {
    List<Category> findAllCategories();

    void addCategory(String categoryName);

    void deleteCategoryByName(String categoryName);

    Category findOneCategoryById(int categoryId);

    Category findOneCategoryByName(String categoryName);

    String findNameById(int categoryId);
}
