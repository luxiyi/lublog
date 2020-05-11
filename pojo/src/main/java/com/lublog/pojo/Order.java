package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 620421603359868704L;

    private Integer oid;

    private String luser;

    private String oname;

    private Integer ocount;

    private Integer bid;

    private Date bdate;

    private Integer flag;

    public Order() {
    }

    public Order(Integer oid, String luser, String oname, Integer ocount, Integer bid, Date bdate, Integer flag) {
        this.oid = oid;
        this.luser = luser;
        this.oname = oname;
        this.ocount = ocount;
        this.bid = bid;
        this.bdate = bdate;
        this.flag = flag;
    }

}