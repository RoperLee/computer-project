package com.computer.boot.vo;

import com.computer.boot.model.Directory;
import com.computer.boot.model.Chapter;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class ChapterTreeVo {
    private Chapter chapter;
    private List<Directory> directoryList;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
    }
}
