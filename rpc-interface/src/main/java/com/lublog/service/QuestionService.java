package com.lublog.service;

import com.lublog.po.Question;

/**
 * @Description: QuestionServicejava类作用描述
 * @Author: lxy
 * @time: 2020/8/15 23:59
 */
public interface QuestionService {
    void addPlanQuestion(String planFirstQuestion, String planSecondQuestion,
                         String planThirdQuestion, String planFirstAnswer,
                         String planSecondAnswer, String questionUser);

    Question queryPlanQuestion();

    void updatePlanQuestion(String planFirstQuestion, String planSecondQuestion,
                            String planThirdQuestion, String planFirstAnswer,
                            String planSecondAnswer, String questionUser);

    Question queryPlanQuestionByUser(String questionUser);
}
