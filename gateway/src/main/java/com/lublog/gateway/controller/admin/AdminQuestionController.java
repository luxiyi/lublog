package com.lublog.gateway.controller.admin;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lublog.constant.SysConstant;
import com.lublog.po.Question;
import com.lublog.po.User;
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
@RequestMapping("/admin")
public class AdminQuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/addPlanQuestion", method = RequestMethod.POST)
    public String addPlanQuestion(String planFirstQuestion, String planSecondQuestion,
                                  String planThirdQuestion, String planFirstAnswer,
                                  String planSecondAnswer, HttpSession session){
        User user = (User)session.getAttribute(SysConstant.CURRENT_USER);
        String questionUser = user.getUserName();
        String msg = "设置问题答案成功!";
        if (StringUtils.isBlank(planFirstQuestion) || StringUtils.isBlank(planSecondQuestion)
                || StringUtils.isBlank(planThirdQuestion) || StringUtils.isBlank(planFirstAnswer)
                    || StringUtils.isBlank(planSecondAnswer) || StringUtils.isBlank(questionUser)){
            log.info("add planQuestion fail,planFirstQuestion = {}, planSecondQuestion = {}, planThirdQuestion = {}, planFirstAnswer = {}, " +
                    "planSecondAnswer = {}, planThirdAnswe = {}, questionUser = {}");
            msg = "设置问题答案失败!";
            return msg;
        }
        Question question = questionService.queryPlanQuestionByUser(questionUser);
        if (question != null){
            log.info("修改问题答案！");
            return updatePlanQuestion(planFirstQuestion, planSecondQuestion, planThirdQuestion,
                    planFirstAnswer, planSecondAnswer, questionUser);
        }
        questionService.addPlanQuestion(planFirstQuestion, planSecondQuestion, planThirdQuestion,
                planFirstAnswer, planSecondAnswer, questionUser);
        log.info("add planQuestion success");
        return msg;
    }


    private String updatePlanQuestion(String planFirstQuestion, String planSecondQuestion,
                                  String planThirdQuestion, String planFirstAnswer,
                                  String planSecondAnswer, String questionUser){
        String msg = "设置问题答案成功!";
        if (StringUtils.isBlank(planFirstQuestion) || StringUtils.isBlank(planSecondQuestion)
                || StringUtils.isBlank(planThirdQuestion) || StringUtils.isBlank(planFirstAnswer)
                || StringUtils.isBlank(planSecondAnswer) || StringUtils.isBlank(questionUser)){
            log.info("update planQuestion fail,planFirstQuestion = {}, planSecondQuestion = {}, planThirdQuestion = {}, planFirstAnswer = {}, " +
                    "planSecondAnswer = {}, questionUser = {}",planFirstQuestion, planSecondQuestion, planThirdQuestion,
                    planFirstAnswer, planSecondAnswer, questionUser);
            msg = "更改问题失败!";
            return msg;
        }
        questionService.updatePlanQuestion(planFirstQuestion, planSecondQuestion, planThirdQuestion,
                planFirstAnswer, planSecondAnswer,questionUser);
        log.info("update planQuestion success");
        return msg;
    }
}
