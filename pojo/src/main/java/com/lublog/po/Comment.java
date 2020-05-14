package com.lublog.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -3674205491304011273L;

    private Integer commentid;

    private String user;

    private Integer blogid;

    private String commentcontent;

    private Date commentdate;

    private Integer flag;

    public Comment() {
    }

}