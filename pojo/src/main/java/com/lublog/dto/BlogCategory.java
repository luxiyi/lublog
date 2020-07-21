package com.lublog.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lublog.po.BlogContent;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述ArchiveCategory 分类归档
 * @Author: lxy
 * @time: 2020/7/20 23:25
 */
@Data
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 6419833311169724080L;

    // 文章发布日期
//    @JSONField(format="yyyy-MM-dd")
    private String date;


    // 文章数量
    private String count;

    private int categoryid;

    // 文章集合
    private List<BlogContent> blogs;

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

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public List<BlogContent> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogContent> blogs) {
        this.blogs = blogs;
    }
}
