package com.computer.boot.controller;

import com.computer.boot.model.PublishMessage;
import com.computer.boot.service.PublishMessageServiceFacade;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by roper on 2017/6/9.
 */
@RestController
public class PublishMessageController {

    private static final Logger logger = LoggerFactory.getLogger(PublishMessageController.class);

    @Autowired
    private PublishMessageServiceFacade publishMessageServiceFacade;

    /**
     * 获取官方快讯
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publish/getPublishMessage")
    public List<PublishMessage> getPublishMessage(HttpServletRequest request) {
        return publishMessageServiceFacade.getPublishMessage();
    }

}
















































