package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述Cart
 * @Author: lxy
 * @time: 2020/4/6 21:39
 */
@Data
public class Cart implements Serializable {

    private static final long serialVersionUID = -33534807910933588L;

    private int bid;
    private String bname;
    private double price;
    private int count;
    private String img;

    public Cart() {
    }

    public Cart(int bid, String bname, double price, int count, String img) {
        this.bid = bid;
        this.bname = bname;
        this.price = price;
        this.count = count;
        this.img = img;
    }

}
