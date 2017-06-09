package com.computer.boot.service;

import com.computer.boot.model.Directory;
import com.computer.boot.model.Question;
import com.computer.boot.vo.SubjectChapterTreeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface SubjectDirectoryServiceFacade {

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param subjectId
     * @return
     */
    SubjectChapterTreeVo getChapterTreeBySubjectId(Integer subjectId);

    /**
     * 根据科目ID和题目类型获取对应的目录（主要是真题目录和模拟题目录）
     *
     * @param subjectId
     * @param issueKind 真题：Exam, 模拟题：Simulate
     * @return
     */
    List<Directory> getIssueDirBySubjectIdAndKind(Integer subjectId, String issueKind);

    /**
     * 获取某个Director下的所有题目，并按questionType分好组
     *
     * @param subjectId
     * @param directoryId
     * @return
     */
    List<List<Question>> getQuestionGroupBySubIdAndDirId(Integer subjectId, Integer directoryId);
}
