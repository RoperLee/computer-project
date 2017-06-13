package com.computer.boot.vo;

import com.computer.boot.model.User;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
public class RankListVo {
    private User self;
    private List<User> allUserList;

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public List<User> getAllUserList() {
        return allUserList;
    }

    public void setAllUserList(List<User> allUserList) {
        this.allUserList = allUserList;
    }
}
