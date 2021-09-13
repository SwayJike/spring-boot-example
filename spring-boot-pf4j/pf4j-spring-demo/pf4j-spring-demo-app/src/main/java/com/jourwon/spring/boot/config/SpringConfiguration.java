package com.jourwon.spring.boot.config;

import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean(name = "pluginManager")
    public SpringPluginManager pluginManager(){
        return new SpringPluginManager();
    }

}
