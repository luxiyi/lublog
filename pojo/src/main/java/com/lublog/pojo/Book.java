package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {

    private static final long serialVersionUID = -1229668087587325869L;

    private Integer bid;

    private String bname;

    private String author;

    private Double price;

    private Integer bcount;

    private String pubdate;

    private String press;

    private String img;

    private String intro;

    private Integer comcount;

    private Integer flag;

    public Book() {
    }

    public Book(int bid, String bname, String author, double price, int bcount, String pubdate, String press, String intro, int comcount, int flag) {
        this.bid = bid;
        this.bname = bname;
        this.author = author;
        this.price = price;
        this.bcount = bcount;
        this.pubdate = pubdate;
        this.press = press;
        this.intro = intro;
        this.comcount = comcount;
        this.flag = flag;
    }


}