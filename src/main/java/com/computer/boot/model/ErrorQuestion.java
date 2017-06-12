package com.computer.boot.model;

/**
 * Created by roper on 2017/6/12.
 */
public class ErrorQuestion extends BasicModelObject {
    private Long userId;
    private Long subjectId;
    private String questionIdList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getQuestionIdList() {
        return questionIdList;
    }

    public void setQuestionIdList(String questionIdList) {
        this.questionIdList = questionIdList;
    }
}
