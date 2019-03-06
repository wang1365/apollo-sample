package com.xiaochuan.apollosample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApolloSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApolloSampleApplication.class, args);
    }

}
