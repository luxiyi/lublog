package com.lublog.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_blogContent")
public class BlogContent implements Serializable {

    private static final long serialVersionUID = -1229668087587325869L;

    private Integer blogId;

    private String title;

    private String author;

    private String content;

    private Double price;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publishTime;

    private String press;

    private String blogCover;

    private String introduce;

    private int tagId;

    private int views;

    private int likes;

    private int commentCount;

    private int categoryId;

    private int flag;

    public BlogContent() {
    }


}