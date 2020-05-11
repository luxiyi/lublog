package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Orderre implements Serializable {

    private static final long serialVersionUID = 1867309320518125607L;

    private Integer oid;

    private String luser;

    private String oname;

    private Integer ocount;

    private Integer bid;

    private Date rdate;

    private Integer flag;

    public Orderre() {
    }

    public Orderre(Integer oid, String luser, String oname, Integer ocount, Integer bid, Date rdate, Integer flag) {
        this.oid = oid;
        this.luser = luser;
        this.oname = oname;
        this.ocount = ocount;
        this.bid = bid;
        this.rdate = rdate;
        this.flag = flag;
    }

}