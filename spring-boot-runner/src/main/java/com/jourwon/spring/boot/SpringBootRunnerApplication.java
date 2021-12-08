package com.jourwon.spring.boot;

import com.jourwon.spring.boot.beanInitmethod.BeanInitMethodImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 启动类
 * Spring Boot启动时的运行方法
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

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            log.info("bean注解创建CommandLineRunner,参数{}", Arrays.toString(args));
        };
    }

    @Bean(initMethod = "runAfterObjectCreated")
    public BeanInitMethodImpl getFunnyBean() {
        return new BeanInitMethodImpl();
    }

}
