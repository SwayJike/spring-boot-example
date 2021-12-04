package com.jourwon.spring.boot.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 实现ApplicationRunner接口
 *
 * 带 -- 为可选参数,不带 -- 为非可选参数
 * 比如: --foo=bar为可选参数 spring为非可选参数
 *
 * @author JourWon
 * @date 2021/12/2
 */
@Slf4j
@Order(1)
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("MyApplicationRunner run方法");
        boolean b1 = args.containsOption("spring.profile.active");
        boolean b2 = args.containsOption("abc");
        log.info("containsOption[spring.profile.active]:{},containsOption[abc]:{}", b1, b2);

        log.info("SourceArgs:{}", Arrays.toString(args.getSourceArgs()));
        log.info("NonOptionArgs:{}", args.getNonOptionArgs());
        log.info("OptionNames:{}", args.getOptionNames());

        log.info("Printing key and value in loop:");
        for (String key : args.getOptionNames()) {
            log.info("key:{}", key);
            log.info("val:{}", args.getOptionValues(key));
        }
    }

}
