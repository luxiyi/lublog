package com.lublog.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述Tag
 * @Author: lxy
 * @time: 2020/5/26 1:16
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = -7860120867763909562L;

    private int tagid;

    private String tagname;

    public Tag() {
    }

    public Tag(int tagid, String tagname) {
        this.tagid = tagid;
        this.tagname = tagname;
    }
}
