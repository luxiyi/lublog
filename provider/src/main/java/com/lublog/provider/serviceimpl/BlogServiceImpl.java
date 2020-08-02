package com.lublog.provider.serviceimpl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.lublog.dto.BlogCategory;
import com.lublog.dto.BlogTag;
import com.lublog.utils.DateUtils;
import com.lublog.provider.dao.BlogMapper;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.vo.BlogShow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述BookServiceImpl
 * @Author: lxy
 * @time: 2020/4/12 22:20
 */
@Component
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public List<BlogShow> findAllByIndex(int index, int count) {
        return blogMapper.findALLByIndex(index, count);

    }

    @Override
    public List<BlogShow> showLastBlogs(int index, int count) {
        return blogMapper.findALLByIndex(index, count);
    }

    @Override
    public int findBlogTotalPage() {
        int total = blogMapper.findBlogTotalPage();
        int var = total % 12 == 0 ? (total / 12) : (total / 12 + 1);
        int totalPage  = var == 0 ? 1: var;
        return totalPage;
    }

    @Override
    public void insertBlog(BlogContent blogContent) {
        blogMapper.insertBook(blogContent);
    }

    @Override
    public BlogShow findAllById(BlogShow blogShow) {
        return blogMapper.findAllById(blogShow);
    }

    @Override
    public BlogShow findBlogById(int bid) {
        return blogMapper.findBlogById(bid);
    }

    @Override
    public void deleteById(int blogId) {
        blogMapper.deletById(blogId);
    }

    @Override
    public List<BlogShow> findAllBook() {
        return blogMapper.findAllBook();
    }

    @Override
    public void updateById(BlogContent blogContent) {
        blogMapper.updateById(blogContent);
    }

    @Override
    public List<BlogShow> findlikeBook(String bname) {
        return blogMapper.findlikeBook(bname);
    }

    @Override
    public void updateComcount(int bid) {
        blogMapper.updateComcount(bid);
    }

    @Override
    public List<BlogContent> findAllByCategoryId(int categoryId) {
        return blogMapper.findAllByCategoryId(categoryId);
    }

    @Override
    public List<BlogContent> findAllByTagId(int tagId) {
        return blogMapper.findAllByTagId(tagId);
    }

    @Override
    public List<BlogCategory> getBlogByCategories(int categoryId) {
        return blogMapper.showArchiveByCategoryId(categoryId);
    }

    @Override
    public List<BlogContent> getBlogsByCategories(int categoryId, Date startDate, Date endDate) {
        return blogMapper.getBlogsByCategories(categoryId, startDate, endDate);
    }

    @Override
    public List<BlogTag> getBlogByTags(int tagId) {
        return blogMapper.showArchiveByTagId(tagId);
    }

    @Override
    public List<BlogContent> getBlogsByTags(int tagId, Date startDate, Date endDate) {
        return blogMapper.getBlogByTags(tagId, startDate, endDate);
    }

    @Override
    public void reduceCommentCount(Integer blogId) {
        blogMapper.reduceCommentCount(blogId);
    }

    @Override
    public BlogShow queryBlogByTitle(String title) {
        return blogMapper.queryBlogByTitle(title);
    }




}
