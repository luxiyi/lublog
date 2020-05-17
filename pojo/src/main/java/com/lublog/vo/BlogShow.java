package com.lublog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述BlogShow
 * @Author: lxy
 * @time: 2020/6/29 0:52
 */
@Data
public class BlogShow implements Serializable {

    private static final long serialVersionUID = 6660510128007365374L;

    private Integer blogid;

    private String title;

    private String author;

    private String content;

    private String pubdate;

    private String blogcover;

    private String introduce;

    private String tagname;

    private int views;

    private int likes;

    private int commentcount;

    private String categoryname;

    private int flag;

    public BlogShow() {

    }
}
