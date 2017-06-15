package com.computer.boot.service.impl;

import com.computer.boot.mapper.HealthCheckMapper;
import com.computer.boot.model.HealthCheck;
import com.computer.boot.service.HealthCheckServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class HealthCheckService implements HealthCheckServiceFacade {
    private static final Logger logger = LoggerFactory.getLogger(SubjectDirectoryService.class);
    @Autowired
    HealthCheckMapper healthCheckMapper;

    @Override
    public HealthCheck getUserById(int id) {
        return healthCheckMapper.getUserById(id);
    }

    @Override
    public String getUserNameById(int id) {
        return healthCheckMapper.getUserNameById(id);
    }

}