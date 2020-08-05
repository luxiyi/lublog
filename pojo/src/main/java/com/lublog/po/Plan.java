package com.lublog.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述Plan
 * @Author: lxy
 * @time: 2020/7/29 1:21
 */
@Data
public class Plan implements Serializable {
    private static final long serialVersionUID = 2880523033406207456L;

    private int planId;
    private String doPlanDetail;
    private String planContent;
    private String planDetail;
    private String planValue;
    private int planYear;
    private int planMonth;
    private int planDay;
    private int planHour;
    private int planMinute;
    private int planSecond;
    private int planStatus;
    private int flag;

    public Plan() {
    }
}
