package com.eventlink.service;

import com.eventlink.dto.req.LoginReqDTO;
import com.eventlink.dto.req.RegisterReqDTO;
import com.eventlink.result.Result;
import org.springframework.stereotype.Service;

public interface UserLoginService {

    Boolean hasUsername(String username);
    Result<String> register(RegisterReqDTO registerReqDTO);

    Result<String> login(LoginReqDTO loginReqDTO);
}
