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
}
