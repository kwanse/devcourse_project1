package com.grepp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DevcourseProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(DevcourseProject1Application.class, args);
    }

}
