package com.jourwon.spring.boot.extensions;

import org.pf4j.Extension;
import org.pf4j.demo.api.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 超人
 * 
 * @author JourWon
 * @date 2021/9/14
 */
@Extension
public class Superman implements Hero {

    private static final Logger log = LoggerFactory.getLogger(Superman.class);

    private final String name = "超人";

    @Override
    public void eat() {
        log.info("{}在吃饭", name);
    }

    @Override
    public void say() {
        log.info("{}在说话", name);
    }

    @Override
    public void walk() {
        log.info("{}在走路", name);
    }

    @Override
    public void run() {
        log.info("{}在跑路", name);
    }

    @Override
    public void attack() {
        log.info("{}在攻击", name);
    }

    @Override
    public void defense() {
        log.info("{}在防御", name);
    }
}
