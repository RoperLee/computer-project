package com.computer.boot.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by roper on 2017/6/16.
 */
public class QuestionVo {
    private Long id;
    private String title;
    private int type;
    private boolean showAnswer;
    private JSONObject content;
    private JSONArray select;
    private String right;
    private JSONObject detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isShowAnswer() {
        return showAnswer;
    }

    public void setShowAnswer(boolean showAnswer) {
        this.showAnswer = showAnswer;
    }

    public JSONArray getSelect() {
        return select;
    }

    public void setSelect(JSONArray select) {
        this.select = select;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    public JSONObject getDetail() {
        return detail;
    }

    public void setDetail(JSONObject detail) {
        this.detail = detail;
    }
}
