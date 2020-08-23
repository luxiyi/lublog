package com.lublog.provider.dao;

import com.lublog.po.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: CategoryMapperjava类作用描述
 * @Author: lxy
 * @time: 2020/5/26 1:57
 */
public interface CategoryMapper {
    @Select("select category_id, category_name from t_category where flag = 0")
    List<Category> findAllCategories();

    @Insert("insert into t_category (category_name) value (#{param1}) ")
    void addCategory(String categoryName);

    //todo 不能真的删，将flag改为1
    @Delete("update t_category set flag = 1 where category_name = #{param1}")
    void deleteCategoryByName(String categoryName);

    @Select("select category_id, category_name from t_category where category_id = #{param1} and flag = 0")
    Category findOneCategoryById(int categoryId);

    @Select("select category_id, category_name from t_category where category_name = #{param1} and flag = 0")
    Category findOneCategoryByName(String categoryName);

    @Select("select category_name from t_category where category_id = #{param1} and flag = 0")
    String findNameById(int categoryId);
}
