package com.lublog.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "t_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = -3674205491304011273L;

    private Integer commentId;

    private String observer;

    private String commenter;

    private Integer blogId;

    private String commentcontent;

    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss", timezone = "GMT+8")
    private Date commentdate;

    private String contact;

    private Integer flag;

    public Comment() {
    }

}