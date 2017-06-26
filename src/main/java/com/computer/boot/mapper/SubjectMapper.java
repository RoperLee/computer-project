package com.computer.boot.mapper;

import com.computer.boot.model.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface SubjectMapper {

    Subject getSubjectById(@Param("id") Long id);

    List<Subject> getAllSubject();

}
