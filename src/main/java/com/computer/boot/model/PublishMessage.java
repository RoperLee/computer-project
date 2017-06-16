package com.computer.boot.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by roper on 2017/6/10.
 */
public class PublishMessage extends BasicModelObject {
    private String title;
    private String content;
    private String origin;
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = format.parse(time);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int month = c.get(Calendar.MONTH) + 1;
        String rMonth = String.valueOf(month);
        if (month < 10) {
            rMonth = "0" + rMonth;
        }
        int day = c.get(Calendar.DAY_OF_MONTH);
        String rDay = String.valueOf(day);
        if (day < 10) {
            rDay = "0" + rDay;
        }

        this.time = rMonth + "/" + rDay;
    }
}
