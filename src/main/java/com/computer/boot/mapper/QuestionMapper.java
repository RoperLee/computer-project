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

    List<Question> getQuestionListBySubDirAndType(@Param("subjectId") int subjectId,
                                                  @Param("directoryId") int directoryId,
                                                  @Param("questionType") String questionType);

    int selectTotalNumber(@Param("subjectId") int subjectId,
                          @Param("directoryId") int directoryId);

}
