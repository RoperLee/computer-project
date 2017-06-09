package com.computer.boot.vo;

import com.computer.boot.model.ExamSubject;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class SubjectChapterVo {

    private ExamSubject ExamSubject;
    private List<ChapterTreeVo> subjectChapterVo;

    public com.computer.boot.model.ExamSubject getExamSubject() {
        return ExamSubject;
    }

    public void setExamSubject(com.computer.boot.model.ExamSubject examSubject) {
        ExamSubject = examSubject;
    }

    public List<ChapterTreeVo> getSubjectChapterVo() {
        return subjectChapterVo;
    }

    public void setSubjectChapterVo(List<ChapterTreeVo> subjectChapterVo) {
        this.subjectChapterVo = subjectChapterVo;
    }
}
