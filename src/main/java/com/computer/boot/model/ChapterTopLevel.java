package com.computer.boot.model;

/**
 * Created by roper on 2017/6/9.
 */
public class ChapterTopLevel extends BasicModelObject{

    private int id;
    private String title;
    private Integer parentSubject;
    private Integer sortKeyNumber;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getParentSubject() { return parentSubject; }

    public void setParentSubject(Integer parentSubject) { this.parentSubject = parentSubject; }

    public Integer getSortKeyNumber() { return sortKeyNumber; }

    public void setSortKeyNumber(Integer sortKeyNumber) { this.sortKeyNumber = sortKeyNumber; }
}
