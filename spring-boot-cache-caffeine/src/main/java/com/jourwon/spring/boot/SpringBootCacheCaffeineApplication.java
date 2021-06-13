package com.jourwon.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/6/12
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jourwon.spring.boot.**.mapper")
public class SpringBootCacheCaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheCaffeineApplication.class, args);
    }

}
