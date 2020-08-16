package com.lublog.provider.serviceimpl;

import com.lublog.po.Question;
import com.lublog.provider.dao.QuestionMapper;
import com.lublog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: java类作用描述QuestionServiceImpl
 * @Author: lxy
 * @time: 2020/8/16 0:20
 */
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void addPlanQuestion(String planFirstQuestion, String planSecondQuestion, String planThirdQuestion, String planFirstAnswer, String planSecondAnswer, String questionUser) {
        questionMapper.addPlanQuestion(planFirstQuestion, planSecondQuestion, planThirdQuestion, planFirstAnswer, planSecondAnswer, questionUser);
    }

    @Override
    public Question queryPlanQuestion() {
        return questionMapper.queryPlanQuestion();
    }

    @Override
    public void updatePlanQuestion(String planFirstQuestion, String planSecondQuestion, String planThirdQuestion, String planFirstAnswer, String planSecondAnswer, String questionUser) {
        questionMapper.updatePlanQuestion(planFirstQuestion, planSecondQuestion, planThirdQuestion, planFirstAnswer, planSecondAnswer, questionUser);
    }

    @Override
    public Question queryPlanQuestionByUser(String questionUser) {
        return questionMapper.queryPlanQuestionByUser(questionUser);
    }

}
