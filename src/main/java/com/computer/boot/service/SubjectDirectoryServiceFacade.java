package com.computer.boot.service;

import com.computer.boot.model.Directory;
import com.computer.boot.model.StoreType;
import com.computer.boot.model.Subject;
import com.computer.boot.vo.DirectoryListVo;
import com.computer.boot.vo.QuestionGroupVo;
import com.computer.boot.vo.SubjectChapterTreeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface SubjectDirectoryServiceFacade {

    /**
     * 获取所有的科目
     *
     * @return
     */
    List<Subject> getAllSubject();

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param subjectId
     * @return
     */
    SubjectChapterTreeVo getChapterTreeBySubjectId(Long subjectId);

    /**
     * 获取subjectId下的directory列表，并且按照  章节、真题、模拟题分组
     *
     * @param subjectId
     * @return
     */
    List<DirectoryListVo> getDirectoryGroupBySubject(Long subjectId);

    /**
     * 根据科目ID和题目类型获取对应的目录（主要是真题目录和模拟题目录）
     *
     * @param subjectId
     * @param issueKind 真题：Exam, 模拟题：Simulate
     * @return
     */
    List<Directory> getIssueDirBySubjectIdAndKind(Long subjectId, int issueKind);

    /**
     * 获取某个Director下的所有题目，并按questionType分好组
     *
     * @param subjectId
     * @param directoryId
     * @return
     */
    QuestionGroupVo getQuestionGroupBySubIdAndDirId(Long subjectId, Long directoryId);

    /**
     * 根据关键词进行模糊查询，分页返回查询列表
     *
     * @param pageStart
     * @param pageSize
     * @param keyWord
     * @return
     */
    QuestionGroupVo queryQuestionListByKeyWord(int pageStart, int pageSize, String keyWord, int subjectId);

    /**
     * 获取推荐搜索关键词
     *
     * @param total
     * @return
     */
    List<String> getRecommendSearchKeyWord(int total);

    /**
     * 插入搜索关键词（已经存在的就不再插入）
     *
     * @param key
     * @return
     */
    boolean insertNewKey(String key);

    /**
     * 删除某个关键词
     *
     * @param key
     * @return
     */
    boolean deleteKey(String key);

    /**
     * 为某个搜索关键词增加权重
     *
     * @param key
     * @param offset
     * @return
     */
    boolean addKeyCount(String key, int offset);

    /**
     * 获取指定 userId 和 subjectId 下的错题集合
     *
     * @param userId
     * @param subjectId
     * @return
     */
    QuestionGroupVo getStoreQuestionList(Long userId, int subjectId, String storeType);

    /**
     * 添加一道错题
     *
     * @param userId
     * @param subjectId
     * @param questionId
     * @return
     */
    boolean addStoreQuestion(Long userId, int subjectId, Long questionId, StoreType storeType);

    /**
     * 删除一道错题
     *
     * @param userId
     * @param subjectId
     * @param questionId
     * @return
     */
    boolean deleteStoreQuestion(Long userId, int subjectId, Long questionId, String storeType);

}
