package com.computer.boot.vo;

import com.computer.boot.model.Directory;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class DirectoryListVo {
    private String type; //章节目录、真题目录、模拟题目录
    private List<Directory> directoryList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Directory> getDirectoryList() {
        return directoryList;
    }

    public void setDirectoryList(List<Directory> directoryList) {
        this.directoryList = directoryList;
    }
}
