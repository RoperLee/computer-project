package com.computer.boot.mapper;

import com.computer.boot.model.StoreQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface StoreQuestionMapper {

    StoreQuestion getStoreQuestionListStr(@Param("userId") Long userId,
                                          @Param("subjectId") int subjectId,
                                          @Param("storeType") String storeType);

    void updateQuestionIdList(@Param("userId") Long userId,
                              @Param("subjectId") int subjectId,
                              @Param("questionIdList") String questionIdList,
                              @Param("storeType") String storeType);

    void insertStoreQuestion(@Param("userId") Long userId,
                             @Param("subjectId") int subjectId,
                             @Param("questionIdList") String questionIdList,
                             @Param("storeType") String storeType);

}
