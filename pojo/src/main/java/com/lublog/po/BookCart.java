package com.lublog.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookCart implements Serializable {

    private static final long serialVersionUID = -9129582613691429522L;

    private Integer cid;

    private String luser;

    private Integer bid;

    private Integer ccount;

    private Integer flag;

    public BookCart() {
    }

    public BookCart(String luser, int bid, int ccount) {
        this.luser = luser;
        this.bid = bid;
        this.ccount = ccount;
    }

}