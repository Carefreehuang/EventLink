package com.eventlink.controller;

import com.eventlink.dto.req.LoginReqDTO;
import com.eventlink.dto.req.RegisterReqDTO;
import com.eventlink.result.Result;
import com.eventlink.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterReqDTO registerReqDTO) {
        return userLoginService.register(registerReqDTO);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginReqDTO loginReqDTO) {
        return userLoginService.login(loginReqDTO);
    }
}
