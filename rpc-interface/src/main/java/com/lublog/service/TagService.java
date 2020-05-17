package com.lublog.service;

import com.lublog.po.Tag;

import java.util.List;

/**
 * @Description: TagServicejava类作用描述
 * @Author: lxy
 * @time: 2020/5/26 1:25
 */
public interface TagService {

    List<Tag> findAllTags();
}
