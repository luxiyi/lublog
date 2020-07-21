package com.lublog.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class BlogContent implements Serializable {

    private static final long serialVersionUID = -1229668087587325869L;

    private Integer blogid;

    private String title;

    private String author;

    private String content;

    private Double price;

    private int bcount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date pubdate;

    private String press;

    private String blogcover;

    private String introduce;

    private int tagid;

    private int views;

    private int likes;

    private int commentcount;

    private int categoryid;

    private int flag;

    public BlogContent() {
    }


}