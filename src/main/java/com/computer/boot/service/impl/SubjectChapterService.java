package com.computer.boot.service.impl;

import com.computer.boot.mapper.ChapterLastLevelMapper;
import com.computer.boot.mapper.ChapterTopLevelMapper;
import com.computer.boot.mapper.ExamSubjectMapper;
import com.computer.boot.model.ChapterTopLevel;
import com.computer.boot.model.ExamSubject;
import com.computer.boot.service.SubjectChapterServiceFacade;
import com.computer.boot.vo.ChapterTreeVo;
import com.computer.boot.vo.SubjectChapterVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class SubjectChapterService implements SubjectChapterServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(SubjectChapterService.class);

    @Autowired
    ExamSubjectMapper examSubjectMapper;
    @Autowired
    ChapterTopLevelMapper chapterTopLevelMapper;
    @Autowired
    ChapterLastLevelMapper chapterLastLevelMapper;


    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param id
     * @return
     */
    @Override
    public SubjectChapterVo getChapterTreeBySubjectId(int id) {
        SubjectChapterVo resultVo = new SubjectChapterVo();
        ExamSubject subjectInfo = examSubjectMapper.getSubjectInfoById(id);
        if (null == subjectInfo) {
            return resultVo;
        }
        resultVo.setExamSubject(subjectInfo);
        List<ChapterTopLevel> firstTitleList = chapterTopLevelMapper.getTopChapterListBySubjectId(id);
        List<ChapterTreeVo> chapterList = new ArrayList<ChapterTreeVo>();
        for (int i = 0; i < firstTitleList.size(); i++) {
            ChapterTopLevel topLevel = firstTitleList.get(i);
            ChapterTreeVo itemVo = new ChapterTreeVo();
            itemVo.setFatherTitleLevel(topLevel);
            itemVo.setChildTitleLevel(chapterLastLevelMapper.getLastChapterListByParentId(topLevel.getId()));
            chapterList.add(itemVo);
        }
        resultVo.setSubjectChapterVo(chapterList);
        return resultVo;
    }


}


