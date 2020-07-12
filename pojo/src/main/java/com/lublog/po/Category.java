package com.lublog.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述Category
 * @Author: lxy
 * @time: 2020/5/26 1:47
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = -7935402875349092275L;

    private int categoryid;

    private String categoryname;

    public Category() {
    }

    public Category(int categoryid, String categoryname) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
    }
}
