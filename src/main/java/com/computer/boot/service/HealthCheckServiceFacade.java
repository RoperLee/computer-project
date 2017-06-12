package com.computer.boot.service;

import com.computer.boot.model.HealthCheck;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/5/15.
 */
@Service
public interface HealthCheckServiceFacade {

    HealthCheck getUserById(int id);

    String getUserNameById(int id);

}