package com.eventlink.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.eventlink.common.Constants;
import com.eventlink.dto.req.LoginReqDTO;
import com.eventlink.dto.req.RegisterReqDTO;
import com.eventlink.dto.req.UpdateUserReqDTO;
import com.eventlink.dto.resp.LoginRespDTO;
import com.eventlink.entity.User;
import com.eventlink.mapper.UserMapper;
import com.eventlink.result.Result;
import com.eventlink.service.UserLoginService;
import com.eventlink.utils.UserHolder;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private static final int ENABLE_STATUS = 1;

    @Autowired
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean hasUsername(String username) {
        User user = userMapper.findByUsername(username);
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
        userMapper.insert(insertUser);
        return Result.success("注册成功");
    }

    @Override
    public Result<LoginRespDTO> login(LoginReqDTO loginReqDTO) {
        if (loginReqDTO == null) {
            return Result.error("参数不能为空");
        }
        if (loginReqDTO.getUsername() == null) {
            return Result.error("用户名不能为空");
        }
        if (loginReqDTO.getPassword() == null) {
            return Result.error("密码不能为空");
        }
        User user = userMapper.findByUsername(loginReqDTO.getUsername());

        if (user == null || !loginReqDTO.getPassword().equals(user.getPassword())) {
            return Result.error("密码错误");
        }
        // 登录成功，生成一个token，存入redis，返回前端
        String token = UUID.randomUUID().toString();
        String tokenKey = Constants.USER_LOGIN_KEY + token;
        // 忽略空值，字段转字符串，hutool的忽略空值好像有bug，所以这里手动处理
        Map<String, Object> userMap = BeanUtil.beanToMap(user,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName,fieldValue) -> {
                            if (fieldValue != null) {
                                return fieldValue.toString();
                            }
                            return "";
                        }));
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        stringRedisTemplate.expire(tokenKey, Constants.USER_LOGIN_TTL, TimeUnit.SECONDS);

        LoginRespDTO loginRespDTO = new LoginRespDTO();
        BeanUtil.copyProperties(user, loginRespDTO);
        loginRespDTO.setToken(token);

        return Result.success("登录成功", loginRespDTO);
    }

    @Override
    public Result<String> logout(String token) {
        UserHolder.removeUser();
        stringRedisTemplate.delete(Constants.USER_LOGIN_KEY + token);
        return Result.success("登出成功",null);
    }

    @Override
    public Result<Long> updateUserInfo(Long id, UpdateUserReqDTO updateUserReqDTO) {
        if (id == null) {
            return Result.error("id不能为空");
        }
        if (UserHolder.getUser() == null || !id.equals(UserHolder.getUser().getId())) {
            return Result.error("无权限");
        }
        if (updateUserReqDTO == null) {
            return Result.error("参数不能为空");
        }

        if (updateUserReqDTO.getUsername() == null) {
            return Result.error("用户名不能为空");
        }
        // 用户名发生变化，检查是否重复
        String username = userMapper.getUsernameById(id);
        if (!updateUserReqDTO.getUsername().equals(username) && hasUsername(updateUserReqDTO.getUsername())) {
            return Result.error("用户名已存在");
        }
        int result = userMapper.updateById(id, updateUserReqDTO);
        if (result > 0) {
            return Result.success("更新成功", id);
        } else {
            return Result.error("更新失败");
        }
    }

    @Override
    public Result<LoginRespDTO> getUserInfo(Long id) {
        if (id == null) {
            return Result.error("id不能为空");
        }
        User user = userMapper.findById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        LoginRespDTO loginRespDTO = new LoginRespDTO();
        BeanUtil.copyProperties(user, loginRespDTO);
        return Result.success("查询成功", loginRespDTO);
    }
}
