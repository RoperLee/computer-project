package com.computer.boot.service;

import com.computer.boot.model.PublishMessage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
@Service
public interface PublishMessageServiceFacade {

    List<PublishMessage> getPublishMessage();
}
