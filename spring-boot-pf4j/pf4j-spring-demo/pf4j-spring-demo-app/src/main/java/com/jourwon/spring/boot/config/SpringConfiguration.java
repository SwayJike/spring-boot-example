package com.jourwon.spring.boot.config;

import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring 配置类
 *
 * @author JourWon
 * @date 2021/9/14
 */
@Configuration
public class SpringConfiguration {

    @Bean(name = "pluginManager")
    public SpringPluginManager pluginManager(){
        return new SpringPluginManager();
    }

}
