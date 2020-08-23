package com.lublog.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: java类作用描述PlanVistor
 * @Author: lxy
 * @time: 2020/8/15 20:11
 */
@Data
@Table(name = "t_planVisitor")
public class PlanVisitor implements Serializable {
    private static final long serialVersionUID = -3005449359079090511L;
    private int planVisitorId;

    private String planVisitorName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planVisitorTime;

    private int planVisitorCount;

    private int planVisitorFlag;

    public PlanVisitor() {
    }
}
