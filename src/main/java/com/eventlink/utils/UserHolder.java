package com.eventlink.utils;

import com.eventlink.entity.User;

/**
 * 用于存储当前登录用户的信息
 */
public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();
    public static void saveUser(User user){
        tl.set(user);
    }
    public static User getUser(){
        return tl.get();
    }
    public static void removeUser(){
        tl.remove();
    }
}
