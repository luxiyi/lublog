package com.lublog.service;


import com.lublog.po.BlogContent;

import java.util.List;

/**
 * @Description: java类作用描述BookService
 * @Author: lxy
 * @time: 2020/4/6 21:55
 */
public interface BookService {
    List<BlogContent> findAllByIndex(int index, int count);
    int findTotalPage();
    void insertBook(BlogContent blogContent);
    BlogContent findAllById(BlogContent blogContent);
    BlogContent findBookById(int bid);
    void deleteById(int blogId);
    List<BlogContent> findAllBook();
    void updateById(BlogContent blogContent);
    List<BlogContent> findlikeBook(String bname);
    void updateComcount(int bid);


}
