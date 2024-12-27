package com.example.eventlink;

import com.example.eventlink.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventLinkApplicationTests {
    @Autowired
    LoginMapper loginMapper;
    @Test
    public void testMybatis() {
        loginMapper.insert();
    }

}
