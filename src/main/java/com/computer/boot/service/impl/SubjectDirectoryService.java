package com.computer.boot.service.impl;

import com.computer.boot.mapper.ChapterMapper;
import com.computer.boot.mapper.DirectoryMapper;
import com.computer.boot.mapper.QuestionMapper;
import com.computer.boot.mapper.SubjectMapper;
import com.computer.boot.model.*;
import com.computer.boot.service.SubjectDirectoryServiceFacade;
import com.computer.boot.vo.ChapterTreeVo;
import com.computer.boot.vo.QuestionGroupVo;
import com.computer.boot.vo.SubjectChapterTreeVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class SubjectDirectoryService implements SubjectDirectoryServiceFacade {

    private static final Logger logger = LoggerFactory.getLogger(SubjectDirectoryService.class);

    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private DirectoryMapper directoryMapper;
    @Autowired
    private QuestionMapper questionMapper;


    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param subjectId
     * @return
     */
    @Override
    public SubjectChapterTreeVo getChapterTreeBySubjectId(Integer subjectId) {
        SubjectChapterTreeVo resultVo = new SubjectChapterTreeVo();
        Subject subject = subjectMapper.getSubjectById(subjectId);
        if (null == subject) {
            return resultVo;
        }
        resultVo.setSubject(subject);
        List<Chapter> ChapterList = chapterMapper.getChapterListBySubjectId(subjectId);
        List<ChapterTreeVo> chapterTreeList = new ArrayList<>();
        for (int i = 0; i < ChapterList.size(); i++) {
            Chapter chapter = ChapterList.get(i);
            ChapterTreeVo itemVo = new ChapterTreeVo();
            itemVo.setChapter(chapter);
            itemVo.setDirectoryList(directoryMapper.getDirectoryListBySubIdAndChapterId(subjectId, chapter.getId()));
            chapterTreeList.add(itemVo);
        }
        resultVo.setChapterTree(chapterTreeList);
        return resultVo;
    }

    /**
     * 根据科目ID和题目类型获取对应的目录（主要是真题目录和模拟题目录）
     *
     * @param subjectId
     * @param issueKind 真题：Exam, 模拟题：Simulate
     * @return
     */
    @Override
    public List<Directory> getIssueDirBySubjectIdAndKind(Integer subjectId, String issueKind) {
        if (null == subjectId || StringUtils.isBlank(issueKind)) {
            logger.info("subjectId or issueKind is empty. subjectId={},issueKind={}", subjectId, issueKind);
            throw new RuntimeException("id and issuekind is necessary,please check again");
        }
        return directoryMapper.getIssueDirBySubjectIdAndKind(subjectId, issueKind);
    }

    /**
     * 获取某个Director下的所有题目，并按questionType分好组
     *
     * @param subjectId
     * @param directoryId
     * @return
     */
    @Override
    public QuestionGroupVo getQuestionGroupBySubIdAndDirId(Integer subjectId, Integer directoryId) {
        QuestionGroupVo resultGroup = new QuestionGroupVo();
        if (null == subjectId || null == directoryId) {
            return resultGroup;
        }
        List<List<Question>> QuestionCollect = new ArrayList<>();
        //循环遍历QuestionType枚举题目类型
        for (QuestionType e : QuestionType.values()) {
            List<Question> tempList = getQuestionListBySubDirAndType(subjectId, directoryId, e);
            if (!CollectionUtils.isEmpty(tempList)) {
                QuestionCollect.add(tempList);
            }
        }
        resultGroup.setTotal(questionMapper.selectTotalNumber(subjectId, directoryId));
        resultGroup.setQuestionGroup(QuestionCollect);
        return resultGroup;
    }

    /**
     * 获取某个directory下指定type类型的题目
     *
     * @param subjectId
     * @param directoryId
     * @param questionType 选择题：CHOICE  填空题：BLANK  操作题：OPERATION
     * @return
     */
    public List<Question> getQuestionListBySubDirAndType(int subjectId, int directoryId, QuestionType questionType) {
        return questionMapper.getQuestionListBySubDirAndType(subjectId, directoryId, questionType.name());
    }


}













































