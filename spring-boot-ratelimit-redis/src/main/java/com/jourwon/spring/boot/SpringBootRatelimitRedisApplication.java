package com.jourwon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/2/18
 */
@SpringBootApplication
public class SpringBootRatelimitRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRatelimitRedisApplication.class, args);
    }

}
