package com.computer.boot.vo;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
public class QuestionGroupVo {

    private Long total;
    private List<List<QuestionVo>> questionGroup;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<List<QuestionVo>> getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(List<List<QuestionVo>> questionGroup) {
        this.questionGroup = questionGroup;
    }
}
