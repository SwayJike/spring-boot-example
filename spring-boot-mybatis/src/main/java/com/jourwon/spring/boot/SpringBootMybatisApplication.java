package com.jourwon.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/1/31
 */
@MapperScan(basePackages = "com.jourwon.spring.boot.**.mapper")
@SpringBootApplication
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

}
