package com.xiaochuan.apollosample.job;

import com.xiaochuan.apollosample.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AppJob {
    @Autowired
    private AppConfig appConfig;

    @Scheduled(cron="* * * * * *")
    public void printApolloConfig() {
        System.out.println("Latest config is: " + appConfig);
    }
}
