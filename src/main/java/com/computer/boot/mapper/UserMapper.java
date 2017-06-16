package com.computer.boot.mapper;

import com.computer.boot.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/14.
 */
@Service
public interface UserMapper {

    User getUser(@Param("userId") Long userId);

    List<User> queryUserRankingList(@Param("pageStart") int pageStart,
                                    @Param("pageSize") int pageSize);

    void addExecuteQuestionCount(@Param("userId") Long userId,
                                 @Param("count") int count);

}
