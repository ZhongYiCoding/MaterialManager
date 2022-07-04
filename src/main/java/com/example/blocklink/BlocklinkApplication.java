package com.example.blocklink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan(value = "com.example.blocklink.mapper")
public class BlocklinkApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlocklinkApplication.class, args);
    }
}
