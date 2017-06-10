package com.computer.boot.model;

/**
 * Created by roper on 2017/6/10.
 */
public class PublishMessage extends BasicModelObject {
    private String title;
    private String content;
    private String origin;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
