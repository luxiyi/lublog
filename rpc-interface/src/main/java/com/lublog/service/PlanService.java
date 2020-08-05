package com.lublog.service;

import com.lublog.po.Plan;

import java.util.List;

/**
 * @Description: java类作用描述PlanService
 * @Author: lxy
 * @time: 2020/7/29 1:30
 */
public interface PlanService {
    void addPlan(String planContent, String planDetail, String planValue, int palnYear, int palnMonth, int palnDay, int palnHour, int palnMinute, int palnSecond, String tagName);

    List<Plan> getTagPlans(String tagName);

    List<Plan> getAllPlans();

    Plan getOnePlan(Integer planId);

    void updateDoDetailPlan(String doPlanDetail,Integer planId);

    void deletePlan(Integer planId);
}
