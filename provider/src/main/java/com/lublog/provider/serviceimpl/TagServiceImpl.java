package com.lublog.provider.serviceimpl;

import com.lublog.po.Tag;
import com.lublog.provider.dao.TagMapper;
import com.lublog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述TagServiceImpl
 * @Author: lxy
 * @time: 2020/5/26 1:24
 */
@Component
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAllTags() {
        return tagMapper.findAllTags();
    }

    @Override
    public void addTag(String tagName) {
        tagMapper.addTag(tagName);
    }

    @Override
    public void deleteTagByName(String tagName) {
        tagMapper.deleteTagByName(tagName);
    }

    @Override
    public Tag findOneTagByName(String tagName) {
        return tagMapper.findOneTagByName(tagName);
    }

    @Override
    public String findNameById(int tagId) {
        return tagMapper.findNameById(tagId);
    }
}
