package com.jourwon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/2/10
 */
@MapperScan(basePackages = "com.jourwon.spring.boot.**.mapper")
@SpringBootApplication
public class SpringBootMybatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisMapperApplication.class, args);
    }

}
