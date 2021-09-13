package com.jourwon.spring.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JediKnightBean {
    private Logger log = LoggerFactory.getLogger(JediKnightBean.class);

    private final String name = "绝地武士";

    public void eat() {
        log.info("{}在吃饭", name);
    }

    public void say() {
        log.info("{}在说话", name);
    }

    public void walk() {
        log.info("{}在走路", name);
    }

    public void run() {
        log.info("{}在跑路", name);
    }

    public void attack() {
        log.info("{}在攻击", name);
    }

    public void defense() {
        log.info("{}在防御", name);
    }
}
