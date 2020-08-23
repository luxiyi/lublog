package com.lublog.dto;

import com.lublog.po.BlogContent;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: java类作用描述BlogTag
 * @Author: lxy
 * @time: 2020/7/22 2:33
 */
public class BlogTag implements Serializable {
    private static final long serialVersionUID = 943634519114495651L;

    // 文章发布日期
    private String date;

    // 文章数量
    private String count;

    private int tagId;

    private List<BlogContent> blogs;

    public BlogTag() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public List<BlogContent> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogContent> blogs) {
        this.blogs = blogs;
    }
}
