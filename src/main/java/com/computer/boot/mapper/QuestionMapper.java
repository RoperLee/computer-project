package com.computer.boot.mapper;

import com.computer.boot.model.Question;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface QuestionMapper {

    List<Question> getQuestionListBySubDirAndType(@Param("subjectId") Long subjectId,
                                                  @Param("directoryId") Long directoryId,
                                                  @Param("questionType") String questionType);

    Long selectTotalNumber(@Param("subjectId") Long subjectId,
                           @Param("directoryId") Long directoryId);

    List<Question> queryQuestionListByKeyWord(@Param("pageStart") int pageStart,
                                              @Param("pageSize") int pageSize,
                                              @Param("keyWord") String keyWord);

    Long queryTotalNumber(@Param("keyWord") String keyWord);

    Question getQuestionById(@Param("id") Long id);

}
