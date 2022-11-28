package com.emilia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.emilia.dao")
public class JunitApplication {
    public static void main(String[] args) {
        SpringApplication.run(JunitApplication.class,args);
    }
}
