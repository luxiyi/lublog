package com.lublog.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: java类作用描述CommentShow
 * @Author: lxy
 * @time: 2020/7/16 2:04
 */
@Data
public class CommentShow implements Serializable {
    private static final long serialVersionUID = -7644129769659661190L;

    private Integer commentId;

    private String observer;

    private String title;

    private String commentcontent;

    @JsonFormat(pattern="yyyy年MM月dd日",timezone="GMT+8")
    private Date commentdate;

    private Integer flag;

    public CommentShow() {
    }
}
