package com.computer.boot.controller;

import com.alibaba.fastjson.JSONArray;
import com.computer.boot.model.HealthCheck;
import com.computer.boot.service.HealthCheckServiceFacade;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @Autowired
    private HealthCheckServiceFacade healthCheckServiceFacade;

    @ResponseBody
    @RequestMapping("/getUserById")
    public HealthCheck getUserById(@RequestParam(value = "id", required = false, defaultValue = "1") int id, HttpServletRequest request) {
        HealthCheck user = healthCheckServiceFacade.getUserById(id);
        if (user != null) {
            logger.info(JSONArray.toJSONString(user));
        }
        return user;
    }

    @ResponseBody
    @RequestMapping("/getUserNameById")
    public String getUserNameById(@RequestParam("id") int id, HttpServletRequest request) {
        String name = healthCheckServiceFacade.getUserNameById(id);
        if (StringUtils.isBlank(name)) {
            logger.info("name is empty");
        }
        return name;
    }

}