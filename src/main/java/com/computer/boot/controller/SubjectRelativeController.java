package com.computer.boot.controller;

import com.computer.boot.model.Directory;
import com.computer.boot.service.SubjectDirectoryServiceFacade;
import com.computer.boot.vo.QuestionGroupVo;
import com.computer.boot.vo.SubjectChapterTreeVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@RestController
public class SubjectRelativeController {

    private static final Logger logger = LoggerFactory.getLogger(SubjectRelativeController.class);

    @Autowired
    private SubjectDirectoryServiceFacade subjectDirectoryServiceFacade;

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param subjectId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getChapterTreeBySubjectId")
    public SubjectChapterTreeVo getChapterBySubjectId(@RequestParam(value = "subjectId") Long subjectId,
                                                      HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getChapterTreeBySubjectId(subjectId);
    }

    /**
     * 根据科目ID和题目类型获取对应的目录（主要是真题目录和模拟题目录）
     *
     * @param subjectId
     * @param issueKind 真题：Exam, 模拟题：Simulate
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getIssueDirBySubjectIdAndKind")
    public List<Directory> getIssueDirBySubjectIdAndKind(@RequestParam(value = "subjectId") Long subjectId,
                                                         @RequestParam(value = "issueKind") String issueKind,
                                                         HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getIssueDirBySubjectIdAndKind(subjectId, issueKind);
    }

    /**
     * 获取某个Director下的所有题目，并按questionType分好组
     *
     * @param subjectId
     * @param directoryId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getQuestionGroup/By/SubIdAndDirId")
    public QuestionGroupVo getQuestionGroupBySubIdAndDirId(@RequestParam(value = "subjectId") Long subjectId,
                                                           @RequestParam(value = "directoryId") Long directoryId,
                                                           HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getQuestionGroupBySubIdAndDirId(subjectId, directoryId);
    }

}
















































