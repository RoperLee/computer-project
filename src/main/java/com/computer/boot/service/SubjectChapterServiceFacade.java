package com.computer.boot.service;

import com.computer.boot.vo.SubjectChapterVo;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/6/9.
 */
@Service
public interface SubjectChapterServiceFacade {

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param id
     * @return
     */
    SubjectChapterVo getChapterTreeBySubjectId(int id);
}
