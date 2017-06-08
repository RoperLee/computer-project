package com.computer.boot.controller;

import com.alibaba.fastjson.JSONArray;
import com.computer.boot.model.User;
import com.computer.boot.service.UserServiceFacade;
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
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceFacade userServiceFacade;

    @ResponseBody
    @RequestMapping("/getUserById")
    public User getUserById(@RequestParam(value = "id", required = false, defaultValue = "1") int id, HttpServletRequest request) {
        User user = userServiceFacade.getUserById(id);
        if (user != null) {
            logger.info(JSONArray.toJSONString(user));
        }
        return user;
    }

    @ResponseBody
    @RequestMapping("/getUserNameById")
    public String getUserNameById(@RequestParam("id") int id, HttpServletRequest request) {
        String name = userServiceFacade.getUserNameById(id);
        if (StringUtils.isBlank(name)) {
            logger.info("name is empty");
        }
        return name;
    }
}