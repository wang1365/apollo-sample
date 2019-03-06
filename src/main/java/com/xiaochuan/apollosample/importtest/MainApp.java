package com.xiaochuan.apollosample.importtest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MainConfig.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for(String name: beanNames) {
            System.out.println("Bean name: " + name);
        }
    }
    }

