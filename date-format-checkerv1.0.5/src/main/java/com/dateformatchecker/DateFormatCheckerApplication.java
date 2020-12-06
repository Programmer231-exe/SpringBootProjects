package com.dateformatchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableRetry
@EnableResourceServer
public class DateFormatCheckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DateFormatCheckerApplication.class, args);
    }

}
