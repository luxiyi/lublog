package com.lublog.service;

import com.lublog.po.PlanVisitor;

/**
 * @Description: PlanVisitorServicejava类作用描述
 * @Author: lxy
 * @time: 2020/8/15 20:29
 */
public interface PlanVisitorService {
    void addPlanVisitor(int planVisitorCount, String planVisitorName);

    PlanVisitor queryPlanVisitor(String planVisitorName);

    void updatePlanVisitor(String planVisitorName);

}
