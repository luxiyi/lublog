package com.lublog.provider.dao;

import com.lublog.po.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: CategoryMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/5/26 1:57
 */
public interface CategoryMapper {
    @Select("select categoryid, categoryname from category")
    List<Category> findAllCategories();
}
