package com.lublog.provider.dao;

import com.lublog.po.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: java类作用描述TagMapper
 * @Author: lxy
 * @time: 2020/5/26 1:24
 */
public interface TagMapper {
    @Select("select tagid,tagname from tag where flag = 0")
    List<Tag> findAllTags();

    @Insert("insert into tag (tagname) value (#{param1}) ")
    void addTag(String tagName);

    //todo 不能真的删，将flag改为1
    @Delete("update tag set flag = 1 where tagname = #{param1} and flag = 0")
    void deleteTagByName(String tagName);

    @Select("select tagid,tagname from tag where tagname = #{param1} and flag = 0")
    Tag findOneTagByName(String tagName);
}
