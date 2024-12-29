package com.eventlink.dto.req;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterReqDTO {

    private String username;

    private String email;

    private String phone;

    private String password;

    private String avatarUrl;

    private Integer role;

    private String schoolName;

    private String className;

    private String major;

}
