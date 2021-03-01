package com.jourwon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/3/1
 */
@EnableAutoConfiguration
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
