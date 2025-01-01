package com.eventlink.config;

import com.eventlink.utils.interceptor.LoginInterceptor;
import com.eventlink.utils.interceptor.RefreshTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
/**
        // 刷新拦截器
        // Interceptor是通过new创建的，所以不是通过Spring容器管理，因此里面的RedisTemplate需要通过构造方法传入
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/**")
                .order(0);

        // 登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                // 排除不需要登录就能访问的路径
                .excludePathPatterns("/user/login", "/user/register")
                .order(1);
 **/
    }
}
