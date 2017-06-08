package com.computer.boot.mapper;

import com.computer.boot.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by roper on 2017/5/15.
 */
@Service
public interface UserMapper {

    User getUserById(@Param("id") int id);

    String getUserNameById(@Param("id") int id);
}
