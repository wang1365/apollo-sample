package com.xiaochuan.apollosample.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Slf4j
public class AppConfig {
    @Value("${info}")
    private String info;

    @ApolloConfigChangeListener
    private void onChange1(ConfigChangeEvent event) {
        log.info("onChange1: {}", event);
    }

    @ApolloConfigChangeListener
    private void onChange2(ConfigChangeEvent event) {
        log.info("onChange2: {}", event);
    }

    @ApolloConfigChangeListener
    private void onChange3(ConfigChangeEvent event) {
        log.info("onChange3: {}", event);
    }
}
