package com.student.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({ "com.student.main", "com.student.model", "com.student.service", "com.student.service.impl", "com.student.web.api" })
@EnableAutoConfiguration
@Configuration

public class Application {
    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}