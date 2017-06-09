package com.computer.boot.controller;

import com.computer.boot.service.SubjectChapterServiceFacade;
import com.computer.boot.vo.SubjectChapterVo;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by roper on 2017/6/9.
 */
@RestController
public class SubjectChapterInfoController {

    private static final Logger logger = LoggerFactory.getLogger(SubjectChapterInfoController.class);

    @Autowired
    private SubjectChapterServiceFacade subjectChapterServiceFacade;

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getChapterTreeBySubjectId")
    public SubjectChapterVo getChapterBySubjectId(@RequestParam(value = "id", required = false, defaultValue = "1") int id,
                                                  HttpServletRequest request) {
        return subjectChapterServiceFacade.getChapterTreeBySubjectId(id);
    }
}