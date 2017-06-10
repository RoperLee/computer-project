package com.computer.boot.service.impl;

import com.computer.boot.mapper.PublishMessageMapper;
import com.computer.boot.model.PublishMessage;
import com.computer.boot.service.PublishMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
@Service
public class PublishMessageService implements PublishMessageServiceFacade {

    @Autowired
    private PublishMessageMapper publishMessageMapper;

    @Override
    public List<PublishMessage> getPublishMessage() {
        return publishMessageMapper.getPublishMessage();
    }
}
