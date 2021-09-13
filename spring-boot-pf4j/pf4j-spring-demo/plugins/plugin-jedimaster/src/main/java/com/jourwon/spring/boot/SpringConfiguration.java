package com.jourwon.spring.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public JediMasterBean jediMaster(){
        return new JediMasterBean();
    }

    @Bean
    JediKnightBean jediKnight(){
        return new JediKnightBean();
    }

}
