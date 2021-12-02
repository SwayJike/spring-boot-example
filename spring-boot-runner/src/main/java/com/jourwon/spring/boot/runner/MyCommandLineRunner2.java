package com.jourwon.spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author JourWon
 * @date 2021/12/2
 */
@Slf4j
@Order(4)
@Component
public class MyCommandLineRunner2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("启动预加载数据(MyCommandLineRunner2)...{}", Arrays.toString(args));
    }

}
