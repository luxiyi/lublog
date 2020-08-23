package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: java类作用描述profile 个人简介
 * @Author: lxy
 * @time: 2020/8/16 21:37
 */
@Data
@Table(name = "t_profile")
public class Profile implements Serializable {
    private static final long serialVersionUID = 9021198265127484621L;
    private int profileId;
    private String profileTitle;
    private String profileContent;
    private String profileCover;
    private String profileIntroduce;
    private int profileViews;
    private int profileFlag;
    public Profile(){

    }
}
