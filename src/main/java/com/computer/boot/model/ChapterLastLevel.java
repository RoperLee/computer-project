package com.computer.boot.model;

/**
 * Created by roper on 2017/6/9.
 */
public class ChapterLastLevel extends BasicModelObject{

    private String title;
    private Integer parentChapter;
    private Integer sortKeyNumber;

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Integer getParentChapter() { return parentChapter; }

    public void setParentChapter(Integer parentChapter) { this.parentChapter = parentChapter; }

    public Integer getSortKeyNumber() { return sortKeyNumber; }

    public void setSortKeyNumber(Integer sortKeyNumber) { this.sortKeyNumber = sortKeyNumber; }
}
