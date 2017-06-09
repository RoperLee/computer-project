package com.computer.boot.model;

/**
 * Created by roper on 2017/6/9.
 */
public class ChapterTopLevel {

    private int id;
    private String title;
    private Integer parentSubject;
    private Integer sortKeyNumber;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getParentSubject() { return parentSubject; }

    public void setParentSubject(Integer parentSubject) { this.parentSubject = parentSubject; }

    public Integer getSortKeyNumber() { return sortKeyNumber; }

    public void setSortKeyNumber(Integer sortKeyNumber) { this.sortKeyNumber = sortKeyNumber; }
}
