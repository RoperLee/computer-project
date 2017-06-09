package com.computer.boot.mapper;

import com.computer.boot.model.ExamSubject;
import org.apache.ibatis.annotations.Param;

/**
 * Created by roper on 2017/6/9.
 */
public interface ExamSubjectMapper {

    ExamSubject getSubjectInfoById(@Param("id") int id);

}
