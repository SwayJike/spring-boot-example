package com.jourwon.spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author JourWon
 * @date 2021/12/2
 */
@Slf4j
@Order(2)
@Component
public class MyApplicationRunner2 implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("启动预加载数据(MyApplicationRunner2)...{},{}", args.getSourceArgs(), args.getOptionNames());
    }

}
