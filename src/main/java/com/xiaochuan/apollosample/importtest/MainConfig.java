package com.xiaochuan.apollosample.importtest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({Circle.class, Square.class,
        MyImportSelector.class, MyImportBeanDefinitionRegister.class})
public class MainConfig {
}
