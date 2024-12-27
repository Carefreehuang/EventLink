package com.example.eventlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.eventlink.mapper")
public class EventLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventLinkApplication.class, args);
    }

}
