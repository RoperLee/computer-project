package com.computer.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roper on 2017/6/10.
 */
@Service
public interface KeyMapper {

    void insertNewKey(@Param("key") String key);

    void deleteKey(@Param("key") String key);

    Long selectTotalNumber(@Param("key") String key);

    void addCount(@Param("key") String key, @Param("offset") int offset);

    List<String> getHotKey(@Param("total") int total);

}
