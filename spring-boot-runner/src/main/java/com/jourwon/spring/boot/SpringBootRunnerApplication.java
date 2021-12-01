package com.jourwon.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/12/1
 */
@Slf4j
@SpringBootApplication
public class SpringBootRunnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunnerApplication.class, args);
        log.info("应用启动...");
    }

}
