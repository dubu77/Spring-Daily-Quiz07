package com.example.springdailyquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.springdailyquiz")
public class SpringDailyQuizApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringDailyQuizApplication.class, args);
    }

}
