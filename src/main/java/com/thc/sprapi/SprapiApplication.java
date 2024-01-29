package com.thc.sprapi;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class SprapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SprapiApplication.class, args);
    }
}
