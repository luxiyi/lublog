package com.lublog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -3674205491304011273L;

    private Integer coid;

    private String luser;

    private Integer bid;

    private String ccont;

    private Date cdate;

    private Integer flag;

    public Comment() {
    }

    public Comment(Integer coid, String luser, Integer bid, String ccont, Date cdate, Integer flag) {
        this.coid = coid;
        this.luser = luser;
        this.bid = bid;
        this.ccont = ccont;
        this.cdate = cdate;
        this.flag = flag;
    }

}