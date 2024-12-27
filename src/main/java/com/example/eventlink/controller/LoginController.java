package com.example.eventlink.controller;

import com.example.eventlink.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginMapper loginMapper;
    @PostMapping("/insert")
    public void insert() {
        loginMapper.insert();
    }
}
