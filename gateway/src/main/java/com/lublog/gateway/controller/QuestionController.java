package com.lublog.gateway.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.constant.SysConstant;
import com.lublog.po.Question;
import com.lublog.po.User;
import com.lublog.service.PlanVisitorService;
import com.lublog.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Description: java类作用描述QuestionController
 * @Author: lxy
 * @time: 2020/8/16 0:34
 */
@RestController
@Slf4j
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PlanVisitorService planVisitorService;

    @RequestMapping(value = "/queryPlanQuestion", method = RequestMethod.GET)
    public Question queryPlanQuestion(){
        Question question = questionService.queryPlanQuestion();
        return question;
    }

}
