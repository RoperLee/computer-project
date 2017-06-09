package com.computer.boot.mapper;

import com.computer.boot.model.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface SubjectMapper {

    Subject getSubjectById(@Param("id") int id);

}
