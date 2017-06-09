package com.computer.boot.service.impl;

import com.computer.boot.service.SubjectChapterServiceFacade;
import com.computer.boot.vo.SubjectChapterVo;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class SubjectChapterService implements SubjectChapterServiceFacade {

    /**
     * 根据科目ID获取该科目下的章节树
     *
     * @param id
     * @return
     */
    @Override
    public SubjectChapterVo getChapterTreeBySubjectId(int id) {
        return null;
    }


}