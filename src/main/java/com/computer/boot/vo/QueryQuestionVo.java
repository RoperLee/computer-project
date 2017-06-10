package com.computer.boot.vo;

import com.computer.boot.model.Question;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
public class QueryQuestionVo {

    private Long total;
    private int pageStart;
    private int pageSize;
    private List<Question> queryResult;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Question> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(List<Question> queryResult) {
        this.queryResult = queryResult;
    }
}
