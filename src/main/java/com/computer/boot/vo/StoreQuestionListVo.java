package com.computer.boot.vo;

import com.computer.boot.model.Question;

import java.util.List;

/**
 * Created by roper on 2017/6/12.
 */
public class StoreQuestionListVo {

    private int total;
    private List<Question> storeQuestionList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Question> getStoreQuestionList() {
        return storeQuestionList;
    }

    public void setStoreQuestionList(List<Question> storeQuestionList) {
        this.storeQuestionList = storeQuestionList;
    }
}
