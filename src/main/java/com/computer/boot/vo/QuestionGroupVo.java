package com.computer.boot.vo;

import com.computer.boot.model.Question;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
public class QuestionGroupVo {

    private int total;
    private List<List<Question>> questionGroup;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<List<Question>> getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(List<List<Question>> questionGroup) {
        this.questionGroup = questionGroup;
    }
}
