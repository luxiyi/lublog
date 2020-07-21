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
    @Select("select categoryid, categoryname from category where flag = 0")
    List<Category> findAllCategories();

    @Insert("insert into category (categoryname) value (#{param1}) ")
    void addCategory(String categoryName);

    //todo 不能真的删，将flag改为1
    @Delete("update category set flag = 1 where categoryname = #{param1}")
    void deleteCategoryByName(String categoryName);

    @Select("select categoryid, categoryname from category where categoryid = #{param1} and flag = 0")
    Category findOneCategoryById(int categoryId);

    @Select("select categoryid, categoryname from category where categoryname = #{param1} and flag = 0")
    Category findOneCategoryByName(String categoryName);
}
