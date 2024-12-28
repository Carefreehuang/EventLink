package com.eventlink;

import com.eventlink.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class EventLinkApplicationTests {
    @Autowired
    LoginMapper loginMapper;
    @Test
    public void testMybatis() {
        loginMapper.insert();
        ArrayList<Integer> list = new ArrayList<>();
    }

}
