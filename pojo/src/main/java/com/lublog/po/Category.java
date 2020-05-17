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

    private int typeid;

    private String typename;

    public Category() {
    }

    public Category(int typeid, String typename) {
        this.typeid = typeid;
        this.typename = typename;
    }
}
