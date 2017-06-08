package com.computer.boot.service.impl;

import com.computer.boot.mapper.UserMapper;
import com.computer.boot.model.User;
import com.computer.boot.service.UserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/5/15.
 */

@Service
public class UserService implements UserServiceFacade {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public String getUserNameById(int id) {
        return userMapper.getUserNameById(id);
    }
}