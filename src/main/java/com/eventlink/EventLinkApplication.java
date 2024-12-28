package com.eventlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eventlink.mapper")
public class EventLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventLinkApplication.class, args);
    }

}
