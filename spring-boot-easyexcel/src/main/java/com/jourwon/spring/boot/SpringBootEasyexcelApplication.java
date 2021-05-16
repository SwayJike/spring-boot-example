package com.jourwon.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/5/16
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jourwon.spring.boot.**.mapper")
public class SpringBootEasyexcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEasyexcelApplication.class, args);
    }

}
