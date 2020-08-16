package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.constant.SysConstant;
import com.lublog.po.PlanVisitor;
import com.lublog.po.Question;
import com.lublog.po.User;
import com.lublog.service.PlanVisitorService;
import com.lublog.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Description: java类作用描述PlanVisitorController
 * @Author: lxy
 * @time: 2020/8/15 20:26
 */
@RestController
@Slf4j
public class PlanVisitorController {
    @Autowired
    private PlanVisitorService planVisitorService;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/queryPlanVisitor")
    public PlanVisitor queryPlanVisitor(String planVisitorName) {
        if (StringUtils.isBlank(planVisitorName)) {
            return null;
        }
        PlanVisitor planVisitor = planVisitorService.queryPlanVisitor(planVisitorName);
        return planVisitor;
    }

    @RequestMapping("/addOrUpdatePlanVisitor")
    public String addOrUpdatePlanVisitor(int planVisitorCount, String planFirstAnswer, String planSecondAnswer,
                                         @RequestParam("planThirdAnswer") String planVisitorName, HttpSession session) {
        String msg = "操作成功";
        if (planVisitorCount == 0 || StringUtils.isBlank(planFirstAnswer) ||
                StringUtils.isBlank(planSecondAnswer) || StringUtils.isBlank(planVisitorName)) {
            msg = "添加失败";
            log.error("addPlanVisitor fail, planVisitorName is {}, planVisitorCount is {}");
            return msg;
        }
        PlanVisitor planVisitor = planVisitorService.queryPlanVisitor(planVisitorName);
        if (planVisitor != null){
            return updatePlanVisitor(planVisitorName,session);
        }
        Question question = questionService.queryPlanQuestion();
        if ((!question.getPlanFirstAnswer().equals(planFirstAnswer)) || (!question.getPlanSecondAnswer().equals(planSecondAnswer))){
            msg = "添加失败";
            log.error("addPlanVisitor fail, planFirstAnswer is {}, planSecondAnswer is {}",planFirstAnswer, planSecondAnswer);
            return msg;
        }
        try {
            planVisitorService.addPlanVisitor(planVisitorCount, planVisitorName);
            log.info("addPlanVisitor success");
            session.setAttribute(SysConstant.ONLINE_VISITOR_SESSION,planVisitorName);
            return msg;
        } catch (Exception e) {
            msg = "添加失败";
            log.error("addPlanVisitor fail, exception is {}",e);
            return msg;
        }

    }

    private String updatePlanVisitor(String planVisitorName, HttpSession session) {
        String msg = "操作成功";
        try {
            planVisitorService.updatePlanVisitor(planVisitorName);
            session.setAttribute(SysConstant.ONLINE_VISITOR_SESSION,planVisitorName);
            log.info("updatePlanVisitor success");
            return msg;
        } catch (Exception e) {
            msg = "更新失败";
            log.error("addPlanVisitor fail, exception is {}");
            return msg;
        }
    }
}
