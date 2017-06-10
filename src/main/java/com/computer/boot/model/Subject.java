package com.computer.boot.model;

/**
 * Created by roper on 2017/6/9.
 */
public class Subject extends BasicModelObject{

    private String fullName;
    private String shortName;
    private Long year;

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getShortName() { return shortName; }

    public void setShortName(String shortName) { this.shortName = shortName; }

    public Long getYear() { return year; }

    public void setYear(Long year) { this.year = year; }

}
