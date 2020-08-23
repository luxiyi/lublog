package com.lublog.provider.serviceimpl;

import com.lublog.po.PlanVisitor;
import com.lublog.provider.dao.PlanVisitorMapper;
import com.lublog.service.PlanVisitorService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述PlanVisitorServiceImpl
 * @Author: lxy
 * @time: 2020/8/15 20:42
 */
public class PlanVisitorServiceImpl implements PlanVisitorService {
    @Autowired
    private PlanVisitorMapper planVisitorMapper;

    @Override
    public void addPlanVisitor(int planVisitorCount, String planVisitorName) {
        planVisitorMapper.addPlanVisitor(planVisitorCount, planVisitorName);
    }

    @Override
    public PlanVisitor queryPlanVisitor(String planVisitorName) {
        return planVisitorMapper.queryPlanVisitor(planVisitorName);
    }

    @Override
    public void updatePlanVisitor(String planVisitorName) {
        planVisitorMapper.updatePlanVisitor(planVisitorName);
    }

}
