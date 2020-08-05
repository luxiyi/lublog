package com.lublog.provider.serviceimpl;

import com.lublog.po.Plan;
import com.lublog.provider.dao.PlanMapper;
import com.lublog.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: java类作用描述PlanServiceImpl
 * @Author: lxy
 * @time: 2020/7/29 1:33
 */
@Component
public class PlanServiceImpl implements PlanService {
    @Autowired
    private PlanMapper planMapper;
    @Override
    public void addPlan(String planContent, String planDetail, String planValue, int planYear, int planMonth, int planDay, int planHour, int planMinute, int planSecond,String tagName) {
        planMapper.addPlan(planContent, planDetail, planValue, planYear, planMonth, planDay, planHour, planMinute, planSecond,tagName);
    }

    @Override
    public List<Plan> getTagPlans(String tagName) {
        return planMapper.getTagPlans(tagName);
    }

    @Override
    public List<Plan> getAllPlans() {
        return planMapper.getAllPlans();
    }

    @Override
    public Plan getOnePlan(Integer planId) {
        return planMapper.getOnePlan(planId);
    }

    @Override
    public void updateDoDetailPlan(String doPlanDetail, Integer planId) {
        planMapper.updateDoDetailPlan(doPlanDetail, planId);
    }

    @Override
    public void deletePlan(Integer planId) {
        planMapper.deletePlan( planId);
    }
}
