package com.eventlink.dto.req;

import lombok.Data;

@Data
public class UpdateUserReqDTO {

    private String email;

    private String phone;

    private String avatarUrl;

    private Integer role;

    private String schoolName;

    private String className;

    private String major;
}
