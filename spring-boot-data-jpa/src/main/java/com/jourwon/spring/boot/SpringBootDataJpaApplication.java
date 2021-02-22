package com.jourwon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类,@EnableJpaRepositories注解用于Srping JPA的代码配置,用于取代xml形式的配置文件
 *
 * @author JourWon
 * @date 2021/2/21
 */
@EnableJpaRepositories(basePackages = {"com.jourwon.spring.boot.dao"})
@SpringBootApplication
public class SpringBootDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataJpaApplication.class, args);
    }

}
