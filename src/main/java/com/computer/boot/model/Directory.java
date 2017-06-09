package com.computer.boot.model;

/**
 * Created by roper on 2017/6/9.
 */
public class Directory extends BasicModelObject{

    private String title;
    private Integer chapterId;
    private Integer subjectId;
    private IssueKind kind;

    public Integer getChapterId() { return chapterId; }

    public void setChapterId(Integer chapterId) { this.chapterId = chapterId; }

    public Integer getSubjectId() { return subjectId; }

    public void setSubjectId(Integer subjectId) { this.subjectId = subjectId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public IssueKind getKind() { return kind; }

    public void setKind(IssueKind kind) { this.kind = kind; }
}
