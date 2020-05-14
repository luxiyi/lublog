package com.lublog.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogContent implements Serializable {

    private static final long serialVersionUID = -1229668087587325869L;

    private Integer blogid;

    private String title;

    private String author;

    private Double price;

    private int bcount;

    private String pubdate;

    private String press;

    private String blogcover;

    private String introduce;

    private int likes;

    private int commentcount;

    private int flag;

    public BlogContent() {
    }


}