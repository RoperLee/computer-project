package com.computer.boot.mapper;

import com.computer.boot.model.ErrorQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface ErrorQuestionMapper {

    ErrorQuestion getErrorQuestionListStr(@Param("userId") Long userId,
                                          @Param("subjectId") int subjectId);

    void updateQuestionIdList(@Param("userId") Long userId,
                              @Param("subjectId") int subjectId,
                              @Param("questionIdList") String questionIdList);

    void insertErrorQuestion(@Param("userId") Long userId,
                             @Param("subjectId") int subjectId,
                             @Param("questionIdList") String questionIdList);

}
