package com.eventlink.controller;

import com.eventlink.dto.req.LoginReqDTO;
import com.eventlink.dto.req.RegisterReqDTO;
import com.eventlink.result.Result;
import com.eventlink.service.UserLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterReqDTO registerReqDTO) {
        return userLoginService.register(registerReqDTO);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginReqDTO loginReqDTO) {
        return userLoginService.login(loginReqDTO);
    }

    @PostMapping("/logout")
    public Result<String> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null) {
            return Result.error("请先登录");
        }
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return Result.error("请先登录");
//        }
//        // 去掉"Bearer "前缀
//        String token = authHeader.substring(7);
        return userLoginService.logout(authHeader);
    }
}
