package com.eventlink.utils.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.eventlink.common.Constants;
import com.eventlink.entity.User;
import com.eventlink.utils.UserHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 刷新token拦截器，用来刷新token的有效期，后续还有login拦截器的拦截
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;
    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求头之中的token
        String token = request.getHeader("authorization");

        // 如果没有token，直接放行
        if(StrUtil.isBlank(token)){
            return true;
        }

        String key = Constants.USER_LOGIN_KEY + token;
        //基于token获取redis中的用户（get是根据key以及字段取值，entries是根据key取值）
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash()
                .entries(key);
        //判断用户是否存在
        if (userMap.isEmpty()){
            return true;
        }

        //将查询到的hash数据转为user
        User user = BeanUtil.fillBeanWithMap(userMap,new User(),false);
        //将userDTO信息保存到threadLocal
        UserHolder.saveUser(user);

        //刷新token的有效期
        stringRedisTemplate.expire(token, Constants.USER_LOGIN_TTL, TimeUnit.SECONDS);

        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除信息，避免内存泄露，新的线程如果有token的话可以从redis中拿到数据
        UserHolder.removeUser();
    }
}
