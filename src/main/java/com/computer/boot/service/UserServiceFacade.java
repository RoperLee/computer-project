package com.computer.boot.service;

import com.computer.boot.model.User;

/**
 * Created by roper on 2017/5/15.
 */
public interface UserServiceFacade {

    User getUserById(int id);

    String getUserNameById(int id);

}