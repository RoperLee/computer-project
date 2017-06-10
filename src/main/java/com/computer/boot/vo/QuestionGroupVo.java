package com.computer.boot.vo;

import com.computer.boot.model.Question;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
public class QuestionGroupVo {

    private Long total;
    private List<List<Question>> questionGroup;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<List<Question>> getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(List<List<Question>> questionGroup) {
        this.questionGroup = questionGroup;
    }
}
