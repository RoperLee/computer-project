package com.computer.boot.service.impl;

import com.computer.boot.mapper.*;
import com.computer.boot.model.*;
import com.computer.boot.service.SubjectDirectoryServiceFacade;
import com.computer.boot.vo.*;
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
    @Autowired
    private KeyMapper keyMapper;
    @Autowired
    private StoreQuestionMapper storeQuestionMapper;
    @Autowired
    private UtilService utilService;


    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param subjectId
     * @return
     */
    @Override
    public SubjectChapterTreeVo getChapterTreeBySubjectId(Long subjectId) {
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
    public List<Directory> getIssueDirBySubjectIdAndKind(Long subjectId, String issueKind) {
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
    public QuestionGroupVo getQuestionGroupBySubIdAndDirId(Long subjectId, Long directoryId) {
        QuestionGroupVo resultGroup = new QuestionGroupVo();
        if (null == subjectId || null == directoryId) {
            return resultGroup;
        }
        List<List<QuestionVo>> QuestionCollect = new ArrayList<>();
        //循环遍历QuestionType枚举题目类型
        for (QuestionType e : QuestionType.values()) {
            List<Question> list = getQuestionListBySubDirAndType(subjectId, directoryId, e);
            List<QuestionVo> tempList = utilService.parseQuestionList2QuestionVoList(list);
            if (!CollectionUtils.isEmpty(tempList)) {
                QuestionCollect.add(tempList);
            }
        }
        resultGroup.setTotal(questionMapper.selectTotalNumber(subjectId, directoryId));
        resultGroup.setQuestionList(QuestionCollect);
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
    public List<Question> getQuestionListBySubDirAndType(Long subjectId, Long directoryId, QuestionType questionType) {
        return questionMapper.getQuestionListBySubDirAndType(subjectId, directoryId, questionType.name());
    }


    /**
     * 根据关键词进行模糊查询，分页返回查询列表
     *
     * @param pageStart
     * @param pageSize
     * @param keyWord
     * @return
     */
    public QuestionGroupVo queryQuestionListByKeyWord(int pageStart, int pageSize, String keyWord, int subjectId) {
        QuestionGroupVo result = new QuestionGroupVo();
        if (pageSize > 2000) {
            pageSize = 2000;
        }
        if (StringUtils.isEmpty(keyWord)) {
            result.setTotal(0L);
            return result;
        }
        List<Question> questionList = questionMapper.queryQuestionListByKeyWord(pageStart, pageSize, keyWord, subjectId);
        List<QuestionVo> choiceList = new ArrayList<>();
        List<QuestionVo> blankList = new ArrayList<>();
        List<QuestionVo> operateList = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            Question item = questionList.get(i);
            QuestionVo vo = utilService.parseQuestion2QuestionVo(item);
            if (QuestionType.CHOICE.name().equalsIgnoreCase(item.getQuestionType())) {
                choiceList.add(vo);
            } else if (QuestionType.BLANK.name().equalsIgnoreCase(item.getQuestionType())) {
                blankList.add(vo);
            } else {
                operateList.add(vo);
            }
        }
        List<List<QuestionVo>> xList = new ArrayList<>();
        xList.add(choiceList);
        xList.add(blankList);
        xList.add(operateList);
        result.setQuestionList(xList);
        result.setTotal(questionMapper.queryTotalNumber(keyWord, subjectId));
        return result;
    }


    /**
     * 获取推荐搜索关键词
     *
     * @param total
     * @return
     */
    public List<String> getRecommendSearchKeyWord(int total) {
        return keyMapper.getHotKey(total);
    }

    /**
     * 插入新搜索关键词
     *
     * @param key
     * @return
     */
    public boolean insertNewKey(String key) {
        //如果已经存在该关键词，则增加一即可
        if (keyMapper.selectTotalNumber(key) > 0) {
            keyMapper.addCount(key, 1);
            return true;
        }
        keyMapper.insertNewKey(key);
        return true;
    }

    /**
     * 删除某个关键词
     *
     * @param key
     * @return
     */
    public boolean deleteKey(String key) {
        keyMapper.deleteKey(key);
        return true;
    }

    /**
     * 为某个搜索关键词增加权重
     *
     * @param key
     * @param offset
     * @return
     */
    public boolean addKeyCount(String key, int offset) {
        keyMapper.addCount(key, offset);
        return true;
    }

    /**
     * 获取指定 userId 和 subjectId 下的错题集合
     *
     * @param userId
     * @param subjectId
     * @return
     */
    public StoreQuestionListVo getStoreQuestionList(Long userId, int subjectId, String storeType) {
        StoreQuestionListVo result = new StoreQuestionListVo();
        StoreQuestion store = storeQuestionMapper.getStoreQuestionListStr(userId, subjectId, storeType);
        if (null == store || StringUtils.isBlank(store.getQuestionIdList())) {
            return result;
        }
        String[] storeIds = store.getQuestionIdList().split(",");
        result.setTotal(storeIds.length);
        List<Question> questionList = new ArrayList<>();
        for (int i = 0; i < storeIds.length; i++) {
            Question item = questionMapper.getQuestionById(Long.valueOf(storeIds[i]));
            questionList.add(item);
        }
        result.setStoreQuestionList(questionList);
        return result;
    }

    /**
     * 添加一道错题
     *
     * @param userId
     * @param subjectId
     * @param questionId
     * @return
     */
    public boolean addStoreQuestion(Long userId, int subjectId, Long questionId, StoreType storeType) {

        StoreQuestion store = storeQuestionMapper.getStoreQuestionListStr(userId, subjectId, storeType.name());
        if (null == store) {
            storeQuestionMapper.insertStoreQuestion(userId, subjectId, String.valueOf(questionId), storeType.name());
            return true;
        }
        if (store.getQuestionIdList().contains(String.valueOf(questionId))) {
            return true;
        }
        String newIdsStr = questionId + "," + store.getQuestionIdList();
        storeQuestionMapper.updateQuestionIdList(userId, subjectId, newIdsStr, storeType.name());
        return true;
    }

    /**
     * 删除一道错题
     *
     * @param userId
     * @param subjectId
     * @param questionId
     * @return
     */
    public boolean deleteStoreQuestion(Long userId, int subjectId, Long questionId, String storeType) {
        StoreQuestion store = storeQuestionMapper.getStoreQuestionListStr(userId, subjectId, storeType);
        if (null == store || StringUtils.isBlank(store.getQuestionIdList())) {
            return true;
        }
        String[] storeIds = store.getQuestionIdList().split(",");
        List<String> afterIds = new ArrayList<>();
        for (int i = 0; i < storeIds.length; i++) {
            if (!String.valueOf(questionId).equalsIgnoreCase(storeIds[i])) {
                afterIds.add(storeIds[i]);
            }
        }
        storeQuestionMapper.updateQuestionIdList(userId, subjectId, StringUtils.join(afterIds.toArray(), ","), storeType);
        return true;
    }

}













































