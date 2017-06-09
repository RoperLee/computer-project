package com.computer.boot.service.impl;

import com.computer.boot.mapper.DirectoryMapper;
import com.computer.boot.mapper.ChapterMapper;
import com.computer.boot.mapper.SubjectMapper;
import com.computer.boot.model.Chapter;
import com.computer.boot.model.Subject;
import com.computer.boot.service.SubjectChapterServiceFacade;
import com.computer.boot.vo.ChapterTreeVo;
import com.computer.boot.vo.SubjectChapterTreeVo;
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
    SubjectMapper subjectMapper;
    @Autowired
    ChapterMapper chapterMapper;
    @Autowired
    DirectoryMapper directoryMapper;


    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param id
     * @return
     */
    @Override
    public SubjectChapterTreeVo getChapterTreeBySubjectId(int id) {
        SubjectChapterTreeVo resultVo = new SubjectChapterTreeVo();
        Subject subject = subjectMapper.getSubjectById(id);
        if (null == subject) {
            return resultVo;
        }
        resultVo.setSubject(subject);
        List<Chapter> ChapterList = chapterMapper.getChapterListBySubjectId(id);
        List<ChapterTreeVo> chapterTreeList = new ArrayList<>();
        for (int i = 0; i < ChapterList.size(); i++) {
            Chapter chapter = ChapterList.get(i);
            ChapterTreeVo itemVo = new ChapterTreeVo();
            itemVo.setChapter(chapter);
            itemVo.setDirectoryList(directoryMapper.getDirectoryListBySubIdAndChapterId(id, chapter.getId()));
            chapterTreeList.add(itemVo);
        }
        resultVo.setChapterTree(chapterTreeList);
        return resultVo;
    }


}


