package com.lublog.service;


import com.lublog.po.BlogContent;
import com.lublog.vo.BlogShow;

import java.util.List;

/**
 * @Description: java类作用描述BookService
 * @Author: lxy
 * @time: 2020/4/6 21:55
 */
public interface BlogService {
    List<BlogShow> findAllByIndex(int index, int count);

    int findTotalPage();

    void insertBlog(BlogContent blogContent);

    BlogShow findAllById(BlogShow blogShow);

    BlogShow findBookById(int bid);

    void deleteById(int blogId);

    List<BlogShow> findAllBook();

    void updateById(BlogContent blogContent);

    List<BlogShow> findlikeBook(String bname);

    void updateComcount(int bid);

}
