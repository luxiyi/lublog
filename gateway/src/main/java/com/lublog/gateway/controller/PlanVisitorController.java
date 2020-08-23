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

import javax.servlet.http.HttpServletRequest;
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
                                         @RequestParam("planThirdAnswer") String planVisitorName, HttpServletRequest request) {
        String msg = "操作成功";
        if (planVisitorCount == 0 || StringUtils.isBlank(planFirstAnswer) ||
                StringUtils.isBlank(planSecondAnswer) || StringUtils.isBlank(planVisitorName)) {
            msg = "请先回答！";
            log.error("addPlanVisitor fail, planVisitorName is {}, planVisitorCount is {}");
            return msg;
        }
        PlanVisitor planVisitor = planVisitorService.queryPlanVisitor(planVisitorName);
        if (planVisitor != null){
            return updatePlanVisitor(planVisitorName,request);
        }
        Question question = questionService.queryPlanQuestion();
        if ((!question.getPlanFirstAnswer().equals(planFirstAnswer)) || (!question.getPlanSecondAnswer().equals(planSecondAnswer))){
            msg = "回答错误，请见网站底部博主联系方式，联系博主吧";
            log.error("addPlanVisitor fail, planFirstAnswer is {}, planSecondAnswer is {}",planFirstAnswer, planSecondAnswer);
            return msg;
        }
        try {
            planVisitorService.addPlanVisitor(planVisitorCount, planVisitorName);
            log.info("addPlanVisitor success");
            request.setAttribute(SysConstant.ONLINE_VISITOR,planVisitorName);
            return msg;
        } catch (Exception e) {
            msg = "系统出错，请联系博主";
            log.error("addPlanVisitor fail, exception is {}",e);
            return msg;
        }

    }

    private String updatePlanVisitor(String planVisitorName, HttpServletRequest request) {
        String msg = "操作成功";
        try {
            planVisitorService.updatePlanVisitor(planVisitorName);
            request.setAttribute(SysConstant.ONLINE_VISITOR,planVisitorName);
            log.info("updatePlanVisitor success");
            return msg;
        } catch (Exception e) {
            msg = "更新失败";
            log.error("addPlanVisitor fail, exception is {}");
            return msg;
        }
    }
}
