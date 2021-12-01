package com.jourwon.spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author JourWon
 * @date 2021/12/2
 */
@Slf4j
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("启动预加载数据(CommandLineRunner)...{}", Arrays.toString(args));
    }

}
