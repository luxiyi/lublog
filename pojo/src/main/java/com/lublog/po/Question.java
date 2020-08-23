package com.lublog.po;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: java类作用描述Question
 * @Author: lxy
 * @time: 2020/8/16 0:00
 */
@Data
@Table(name = "t_question")
public class Question implements Serializable {
    private static final long serialVersionUID = -1855349309890723559L;
    private int questionId;

    private String questionUser;

    private String planFirstQuestion;

    private String planFirstAnswer;

    private String planSecondQuestion;

    private String planSecondAnswer;

    private String planThirdQuestion;

    private String planThirdAnswer;

    private int questionFlag;

    private Question(){

    }
}
