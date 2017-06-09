package com.computer.boot.vo;

import com.computer.boot.model.Subject;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class SubjectChapterTreeVo {

    private Subject subject;
    private List<ChapterTreeVo> chapterTree;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<ChapterTreeVo> getChapterTree() {
        return chapterTree;
    }

    public void setChapterTree(List<ChapterTreeVo> chapterTree) {
        this.chapterTree = chapterTree;
    }
}
