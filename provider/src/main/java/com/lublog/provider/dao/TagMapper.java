package com.lublog.provider.dao;

import com.lublog.po.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: java类作用描述TagMapper
 * @Author: lxy
 * @time: 2020/5/26 1:24
 */
public interface TagMapper {
    @Select("select tagid,tagname from tag")
    List<Tag> findAllTags();
}
