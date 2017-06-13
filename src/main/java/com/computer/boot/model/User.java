package com.computer.boot.model;

/**
 * Created by roper on 2017/6/14.
 */
public class User extends BasicModelObject {
    private int openId;
    private String thirdPlatform;
    private String headImgUrl;
    private String sex;
    private String name;
    private int questionCount;
    private int ranking;

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public String getThirdPlatform() {
        return thirdPlatform;
    }

    public void setThirdPlatform(String thirdPlatform) {
        this.thirdPlatform = thirdPlatform;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }
}
