package com.computer.boot.vo;

import com.computer.boot.model.ChapterLastLevel;
import com.computer.boot.model.ChapterTopLevel;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class ChapterTreeVo {
    private ChapterTopLevel fatherTitleLevel;
    private List<ChapterLastLevel> childTitleLevel;

    public ChapterTopLevel getFatherTitleLevel() {
        return fatherTitleLevel;
    }

    public void setFatherTitleLevel(ChapterTopLevel fatherTitleLevel) {
        this.fatherTitleLevel = fatherTitleLevel;
    }

    public List<ChapterLastLevel> getChildTitleLevel() {
        return childTitleLevel;
    }

    public void setChildTitleLevel(List<ChapterLastLevel> childTitleLevel) {
        this.childTitleLevel = childTitleLevel;
    }
}
