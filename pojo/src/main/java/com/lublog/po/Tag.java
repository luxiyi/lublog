package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: java类作用描述Tag
 * @Author: lxy
 * @time: 2020/5/26 1:16
 */
@Data
@Table(name = "t_tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = -7860120867763909562L;

    private int tagId;

    private String tagName;

    public Tag() {
    }

    public Tag(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }
}
