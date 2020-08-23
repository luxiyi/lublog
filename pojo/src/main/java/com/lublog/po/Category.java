package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: java类作用描述Category
 * @Author: lxy
 * @time: 2020/5/26 1:47
 */
@Data
@Table(name = "t_category")
public class Category implements Serializable {
    private static final long serialVersionUID = -7935402875349092275L;

    private int categoryId;

    private String categoryName;

    public Category() {
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
