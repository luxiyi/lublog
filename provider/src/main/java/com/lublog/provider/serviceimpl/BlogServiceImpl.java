package com.lublog.provider.serviceimpl;

import com.lublog.provider.dao.BlogMapper;
import com.lublog.po.BlogContent;
import com.lublog.service.BlogService;
import com.lublog.vo.BlogShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述BookServiceImpl
 * @Author: lxy
 * @time: 2020/4/12 22:20
 */
@Component
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public List<BlogShow> findAllByIndex(int index, int count) {
        return blogMapper.findALLByIndex(index, count);

    }

    @Override
    public int findTotalPage() {
        int total = blogMapper.findTotalPage();
        int totalPage = total % 12 == 0 ? (total / 12) : (total / 12 + 1);
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
    public BlogShow findBookById(int bid) {
        return blogMapper.findBookById(bid);
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

}
