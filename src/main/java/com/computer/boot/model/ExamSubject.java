package com.computer.boot.model;

import java.util.Date;

/**
 * Created by roper on 2017/6/9.
 */
public class ExamSubject {

    private int id;
    private Date gmtCreat;
    private String isDeleted;
    private String fullName;
    private String shortName;
    private Integer year;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getGmtCreat() { return gmtCreat; }

    public void setGmtCreat(Date gmtCreat) { this.gmtCreat = gmtCreat; }

    public String getIsDeleted() { return isDeleted; }

    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getShortName() { return shortName; }

    public void setShortName(String shortName) { this.shortName = shortName; }

    public Integer getYear() { return year; }

    public void setYear(Integer year) { this.year = year; }

}
