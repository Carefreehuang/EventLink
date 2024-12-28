package com.eventlink.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 用户头像URL
     */
    private String avatarUrl;

    /**
     * 用户角色 0：student，1：admin
     */
    private Integer role;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 班级
     */
    private String className;

    /**
     * 专业
     */
    private String major;

    /**
     * 用户状态 0：禁用，1：启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
