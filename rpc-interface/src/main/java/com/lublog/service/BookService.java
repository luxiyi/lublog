package com.lublog.service;


import com.lublog.pojo.Book;

import java.util.List;

/**
 * @Description: java类作用描述BookService
 * @Author: lxy
 * @time: 2020/4/6 21:55
 */
public interface BookService {
    List<Book> findAllByIndex(int index, int count);
    int findTotalPage();
    void insertBook(Book book);
    Book findAllById(Book book);
    Book findBookById(int bid);
    void deleteById(Book book);
    List<Book> findAllBook();
    void updateById(Book book);
    List<Book> findlikeBook(String bname);
    void updateComcount(int bid);


}
