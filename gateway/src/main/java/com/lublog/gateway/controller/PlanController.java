package com.lublog.gateway.controller;

import com.alibaba.fastjson.JSON;
import com.lublog.po.Plan;
import com.lublog.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述LifeController
 * @Author: lxy
 * @time: 2020/7/29 1:03
 */
@RestController
@Slf4j
public class PlanController {
    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/getAllPlans", method = RequestMethod.GET)
    public List<Plan> getAllPlans() {
        List<Plan> plans = planService.getAllPlans();
        log.info("plans is {}", JSON.toJSONString(plans));
        return plans;
    }



    @RequestMapping(value = "/showDoDetailPlan", method = RequestMethod.GET)
    public Plan showDoDetailPlan(Integer planId) {
        Plan plan = planService.getOnePlan(planId);
        log.info("plan is {}", JSON.toJSONString(plan));
        return plan;
    }
}
