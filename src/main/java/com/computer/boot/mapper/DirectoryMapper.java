package com.computer.boot.mapper;

import com.computer.boot.model.Directory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface DirectoryMapper {

    List<Directory> getDirectoryListBySubIdAndChapterId(@Param("subjectId") int subjectId,
                                                        @Param("chapterId") int chapterId);

    List<Directory> getIssueDirBySubjectIdAndKind(@Param("subjectId") int subjectId,
                                                  @Param("issueKind") String issueKind);

}
