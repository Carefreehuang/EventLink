package com.eventlink.mapper;

import com.eventlink.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    void insert(User user);

    User findByUsername(String username);

}
