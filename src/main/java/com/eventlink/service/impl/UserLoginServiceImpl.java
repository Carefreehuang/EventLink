package com.eventlink.service.impl;

import com.eventlink.dto.req.LoginReqDTO;
import com.eventlink.dto.req.RegisterReqDTO;
import com.eventlink.entity.User;
import com.eventlink.mapper.LoginMapper;
import com.eventlink.result.Result;
import com.eventlink.service.UserLoginService;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private static final int ENABLE_STATUS = 1;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Boolean hasUsername(String username) {
        User user = loginMapper.findByUsername(username);
        return user != null;
    }

    @Override
    public Result<String> register(RegisterReqDTO registerReqDTO) {
        if (registerReqDTO == null) {
            return Result.error("参数不能为空");
        }
        if (registerReqDTO.getUsername() == null || registerReqDTO.getPassword() == null) {
            return Result.error("用户名或密码不能为空");
        }
        if (registerReqDTO.getEmail() == null) {
            return Result.error("邮箱不能为空");
        }
        if (hasUsername(registerReqDTO.getUsername())) {
            return Result.error("用户名已存在");
        }
        User insertUser = new User();
        BeanUtils.copyProperties(registerReqDTO, insertUser);
        insertUser.setStatus(ENABLE_STATUS);
        loginMapper.insert(insertUser);
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(LoginReqDTO loginReqDTO) {
        if (loginReqDTO == null) {
            return Result.error("参数不能为空");
        }
        if (loginReqDTO.getUsername() == null) {
            return Result.error("用户名不能为空");
        }
        if (loginReqDTO.getPassword() == null) {
            return Result.error("密码不能为空");
        }
        User user = loginMapper.findByUsername(loginReqDTO.getUsername());

        if (user == null || !loginReqDTO.getPassword().equals(user.getPassword())) {
            return Result.error("密码错误");
        }
        return Result.success("登录成功");
    }
}
