package com.eventlink.utils.interceptor;

import com.eventlink.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 解决复杂请求的预验请求问题
        if (request.getMethod().equals("OPTIONS")){
            return true;
        }

        // 检查是否用户处于登录状态
        if (UserHolder.getUser() == null){
            response.setStatus(401);
            return false;
        }
        return true;
    }
}