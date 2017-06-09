package com.computer.boot.mapper;

import com.computer.boot.model.ChapterLastLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public interface ChapterLastLevelMapper {

    List<ChapterLastLevel> getLastChapterListByParentId(@Param("parentId") int parentId);

}
