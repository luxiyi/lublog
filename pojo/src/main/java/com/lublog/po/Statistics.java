package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: java类作用描述Statistics 统计数据
 * @Author: lxy
 * @time: 2020/8/22 15:35
 */
@Data
@Table(name = "t_statistics")
public class Statistics implements Serializable {
    private static final long serialVersionUID = -928168800221204381L;
    private int statisticsId;

    private int pageViewNum;

    private int blogLikesNum;

    private int blogCommentsNum;

    private int blogTotalNum;

    private int blogShowNum;

    private int blogDeleteNum;

    private int flag;

    public Statistics(){

    }
}
