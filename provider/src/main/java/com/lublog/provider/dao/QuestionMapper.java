package com.lublog.provider.dao;

import com.lublog.po.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @Description: java类作用描述QuestionMapper
 * @Author: lxy
 * @time: 2020/8/16 0:21
 */
public interface QuestionMapper {
    @Insert("insert into t_question (plan_first_question,plan_second_question,plan_third_question," +
            "plan_first_answer,plan_second_answer,question_user) value " +
            "(#{param1}, #{param2},#{param3},#{param4}, #{param5}, #{param6})")
    void addPlanQuestion(String planFirstQuestion, String planSecondQuestion,
                         String planThirdQuestion, String planFirstAnswer,
                         String planSecondAnswer, String questionUser);

    @Select("select * from t_question where flag = 0")
    Question queryPlanQuestion();

    @Insert("update t_question set plan_first_question = #{param1},plan_second_question = #{param2}," +
            "plan_third_question = #{param3},plan_first_answer = #{param4},plan_second_answer = #{param5}" +
            "where question_user = #{param6} and flag = 0")
    void updatePlanQuestion(String planFirstQuestion, String planSecondQuestion,
                            String planThirdQuestion, String planFirstAnswer,
                            String planSecondAnswer, String questionUser);

    @Select("select * from t_question where question_user = #{param1} and flag = 0")
    Question queryPlanQuestionByUser(String questionUser);

}
