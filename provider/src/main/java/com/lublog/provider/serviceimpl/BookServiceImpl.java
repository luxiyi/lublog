package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.BookMapper;
import com.lublog.po.BlogContent;
import com.lublog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述BookServiceImpl
 * @Author: lxy
 * @time: 2020/4/12 22:20
 */
@Component
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<BlogContent> findAllByIndex(int index, int count) {
        return bookMapper.findALLByIndex(index, count);

    }

    @Override
    public int findTotalPage() {
        int total = bookMapper.findTotalPage();
        int totalPage = total % 12 == 0 ? (total / 12) : (total / 12 + 1);
        return totalPage;
    }

    @Override
    public void insertBook(BlogContent blogContent) {
        bookMapper.insertBook(blogContent);
    }

    @Override
    public BlogContent findAllById(BlogContent blogContent) {
        return bookMapper.findAllById(blogContent);
    }

    @Override
    public BlogContent findBookById(int bid) {
        return bookMapper.findBookById(bid);
    }

    @Override
    public void deleteById(int blogId) {
        bookMapper.deletById(blogId);
    }

    @Override
    public List<BlogContent> findAllBook() {
        return bookMapper.findAllBook();
    }

    @Override
    public void updateById(BlogContent blogContent) {
        bookMapper.updateById(blogContent);
    }

    @Override
    public List<BlogContent> findlikeBook(String bname) {
        return bookMapper.findlikeBook(bname);
    }

    @Override
    public void updateComcount(int bid) {
        bookMapper.updateComcount(bid);
    }


}