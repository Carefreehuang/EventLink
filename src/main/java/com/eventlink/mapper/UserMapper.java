package com.eventlink.mapper;

import com.eventlink.dto.req.UpdateUserReqDTO;
import com.eventlink.dto.resp.LoginRespDTO;
import com.eventlink.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insert(User user);

    User findByUsername(String username);

    int updateById(@Param("id") Long id, @Param("data") UpdateUserReqDTO updateUserReqDTO);

    User findById(Long id);

    String getUsernameById(Long id);

}
