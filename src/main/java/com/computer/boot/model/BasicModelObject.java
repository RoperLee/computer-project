package com.computer.boot.model;

import java.util.Date;

/**
 * Created by roper on 2017/6/9.
 */
public class BasicModelObject {

    private int id;
    private Date gmtCreat;
    private String isDeleted;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getGmtCreat() { return gmtCreat; }

    public void setGmtCreat(Date gmtCreat) { this.gmtCreat = gmtCreat; }

    public String getIsDeleted() { return isDeleted; }

    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }
}
