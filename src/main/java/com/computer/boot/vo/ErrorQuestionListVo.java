package com.computer.boot.vo;

import com.computer.boot.model.Question;

import java.util.List;

/**
 * Created by roper on 2017/6/12.
 */
public class ErrorQuestionListVo {

    private int total;
    private List<Question> errorQuestionList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Question> getErrorQuestionList() {
        return errorQuestionList;
    }

    public void setErrorQuestionList(List<Question> errorQuestionList) {
        this.errorQuestionList = errorQuestionList;
    }
}
