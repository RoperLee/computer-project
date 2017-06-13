package com.computer.boot.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by roper on 2017/5/15.
 */
@Service
public interface UtilServiceFacade {

    void showPic(HttpServletResponse response, String fileName);

    int getExamDate();
}