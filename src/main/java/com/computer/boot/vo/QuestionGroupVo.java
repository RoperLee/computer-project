package com.computer.boot.vo;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
public class QuestionGroupVo {

    private Long total;
    private List<List<QuestionVo>> questionList;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<List<QuestionVo>> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<List<QuestionVo>> questionList) {
        this.questionList = questionList;
    }
}
