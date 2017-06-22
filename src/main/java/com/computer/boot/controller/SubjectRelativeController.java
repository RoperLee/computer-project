package com.computer.boot.controller;

import com.computer.boot.model.Directory;
import com.computer.boot.model.StoreType;
import com.computer.boot.model.Subject;
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
     * 获取所有的科目
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllSubject")
    public List<Subject> getAllSubject(HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getAllSubject();
    }

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param subjectId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getOfficialBook")
    public SubjectChapterTreeVo getChapterBySubjectId(@RequestParam(value = "subjectId") Long subjectId,
                                                      HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getChapterTreeBySubjectId(subjectId);
    }

    /**
     * 根据科目ID和题目类型获取对应的目录（主要是真题目录和模拟题目录）
     *
     * @param subjectId
     * @param issueKind 真题：1-Exam, 模拟题：2-Simulate
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getIssueDirBySubjectIdAndKind")
    public List<Directory> getIssueDirBySubjectIdAndKind(@RequestParam(value = "subjectId") Long subjectId,
                                                         @RequestParam(value = "issueKind") int issueKind,
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


    /**
     * 根据关键词进行模糊查询，分页返回查询列表
     *
     * @param pageStart
     * @param pageSize
     * @param key
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/queryQuestionListByKeyWord")
    public QuestionGroupVo queryQuestionListByKeyWord(@RequestParam(value = "pageStart") int pageStart,
                                                      @RequestParam(value = "pageSize") int pageSize,
                                                      @RequestParam(value = "key") String key,
                                                      @RequestParam(value = "subjectId") int subjectId,
                                                      HttpServletRequest request) {
        return subjectDirectoryServiceFacade.queryQuestionListByKeyWord(pageStart, pageSize, key, subjectId);
    }


    /**
     * 获取推荐搜索关键词
     *
     * @param total
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getRecommendSearchKeyWord")
    public List<String> getRecommendSearchKeyWord(@RequestParam(value = "total") int total,
                                                  HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getRecommendSearchKeyWord(total);
    }

    /**
     * 插入搜索关键词（已经存在的就不再插入）
     *
     * @param key
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/insertNewKey")
    public boolean insertNewKey(@RequestParam(value = "key") String key, HttpServletRequest request) {
        return subjectDirectoryServiceFacade.insertNewKey(key);
    }

    /**
     * 删除某个搜索关键词
     *
     * @param key
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/deleteKey")
    public boolean deleteKey(@RequestParam(value = "key") String key, HttpServletRequest request) {
        return subjectDirectoryServiceFacade.deleteKey(key);
    }

    /**
     * 为某个搜索关键词增加权重
     *
     * @param key
     * @param offset
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/addKeyCount")
    public boolean addKeyCount(@RequestParam(value = "key") String key,
                               @RequestParam(value = "offset") int offset,
                               HttpServletRequest request) {
        return subjectDirectoryServiceFacade.addKeyCount(key, offset);
    }

    /**
     * 获取指定 userId 和 subjectId 下的错题/收藏题集合
     *
     * @param userId
     * @param subjectId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/getStoreQuestionList")
    public QuestionGroupVo getStoreQuestionList(@RequestParam(value = "userId") Long userId,
                                                @RequestParam(value = "subjectId") int subjectId,
                                                @RequestParam(value = "storeType") String storeType,
                                                HttpServletRequest request) {
        return subjectDirectoryServiceFacade.getStoreQuestionList(userId, subjectId, storeType);
    }

    /**
     * 添加一道错题／收藏题
     *
     * @param userId
     * @param subjectId
     * @param questionId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/addStoreQuestion")
    public boolean addStoreQuestion(@RequestParam(value = "userId") Long userId,
                                    @RequestParam(value = "subjectId") int subjectId,
                                    @RequestParam(value = "questionId") Long questionId,
                                    @RequestParam(value = "storeType") StoreType storeType,
                                    HttpServletRequest request) {
        return subjectDirectoryServiceFacade.addStoreQuestion(userId, subjectId, questionId, storeType);
    }

    /**
     * 删除一道错题／收藏题
     *
     * @param userId
     * @param subjectId
     * @param questionId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/subject/deleteStoreQuestion")
    public boolean deleteStoreQuestion(@RequestParam(value = "userId") Long userId,
                                       @RequestParam(value = "subjectId") int subjectId,
                                       @RequestParam(value = "questionId") Long questionId,
                                       @RequestParam(value = "storeType") String storeType,
                                       HttpServletRequest request) {
        return subjectDirectoryServiceFacade.deleteStoreQuestion(userId, subjectId, questionId, storeType);
    }


}
















































