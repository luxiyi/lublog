package com.lublog.service;


import com.lublog.dto.BlogCategory;
import com.lublog.dto.BlogTag;
import com.lublog.po.BlogContent;
import com.lublog.vo.BlogShow;

import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述BookService
 * @Author: lxy
 * @time: 2020/4/6 21:55
 */
public interface BlogService {
    List<BlogShow> findAllByIndex(int index, int count);

    List<BlogShow> showLastArticle(int index, int count);

    int findBlogTotalPage();

    void insertBlog(BlogContent blogContent);

    BlogShow findAllById(BlogShow blogShow);

    BlogShow findBlogById(int bid);

    void deleteById(int blogId);

    List<BlogShow> findAllBook();

    void updateById(BlogContent blogContent);

    List<BlogShow> findlikeBook(String bname);

    void updateComcount(int bid);

    List<BlogContent> findAllByCategoryId(int categoryId);

    List<BlogContent> findAllByTagId(int tagId);

    List<BlogCategory> getBlogByCategories(int categoryId);

    List<BlogContent> getBlogsByCategories(int categoryId, Date startDate, Date endDate);

    List<BlogTag> getBlogByTags(int tagId);

    List<BlogContent> getBlogsByTags(int id, Date startDate, Date endDate);

    void reduceCommentCount(Integer blogId);

}
