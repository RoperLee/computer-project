package com.computer.boot.model;

/**
 * Created by roper on 2017/6/9.
 */
public class Chapter extends BasicModelObject {

    private String title;
    private Long subjectId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
