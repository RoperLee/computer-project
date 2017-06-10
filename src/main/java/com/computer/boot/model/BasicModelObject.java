package com.computer.boot.model;

import java.util.Date;

/**
 * Created by roper on 2017/6/9.
 */
public class BasicModelObject {

    private Long id;
    private Date gmtCreat;
    private String isDeleted;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getGmtCreat() { return gmtCreat; }

    public void setGmtCreat(Date gmtCreat) { this.gmtCreat = gmtCreat; }

    public String getIsDeleted() { return isDeleted; }

    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }
}
