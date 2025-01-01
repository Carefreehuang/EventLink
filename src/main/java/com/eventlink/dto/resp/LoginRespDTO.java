package com.eventlink.dto.resp;

import lombok.Data;

@Data
public class LoginRespDTO {

    private String token;

    private Long id;

    private String username;

    private String email;

    private String phone;

    private String avatarUrl;

    private Integer role;

    private String schoolName;

    private String className;

    private String major;

    private Integer status;
}
