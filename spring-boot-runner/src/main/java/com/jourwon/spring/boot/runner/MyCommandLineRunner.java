package com.jourwon.spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 实现CommandLineRunner接口
 *
 * @author JourWon
 * @date 2021/12/2
 */
@Slf4j
@Order(2)
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("MyCommandLineRunner run方法...{}", Arrays.toString(args));
    }

}
