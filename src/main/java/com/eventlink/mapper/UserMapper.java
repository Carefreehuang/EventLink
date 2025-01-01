package com.eventlink.mapper;

import com.eventlink.dto.req.UpdateUserReqDTO;
import com.eventlink.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void insert(User user);

    User findByUsername(String username);

    int updateById(Long id, UpdateUserReqDTO updateUserReqDTO);
}
