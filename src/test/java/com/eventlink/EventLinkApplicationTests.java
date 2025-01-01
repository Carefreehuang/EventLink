package com.eventlink;

import com.eventlink.entity.User;
import com.eventlink.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventLinkApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Test
    public void testMybatis() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setEmail("2380520989@qq.com");
        userMapper.insert(user);
    }

}
