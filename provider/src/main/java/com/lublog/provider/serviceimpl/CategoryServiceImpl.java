package com.lublog.provider.serviceimpl;

import com.lublog.po.Category;
import com.lublog.provider.dao.CategoryMapper;
import com.lublog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述CategoryServiceImpl
 * @Author: lxy
 * @time: 2020/5/26 1:55
 */
@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findAllCategories() {
        return categoryMapper.findAllCategories();
    }

    @Override
    public void addCategory(String categoryName) {
        categoryMapper.addCategory(categoryName);
    }

    @Override
    public void deleteCategoryByName(String categoryName) {
        categoryMapper.deleteCategoryByName(categoryName);
    }

    @Override
    public Category findOneCategoryById(int categoryId) {
        return categoryMapper.findOneCategoryById(categoryId);
    }

    @Override
    public Category findOneCategoryByName(String categoryName) {
        return categoryMapper.findOneCategoryByName(categoryName);
    }

    @Override
    public String findNameById(int categoryId) {
        return categoryMapper.findNameById(categoryId);
    }
}
