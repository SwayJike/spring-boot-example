package com.jourwon.spring.boot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupernaturalAbilityImpl implements SupernaturalAbility{
    private Logger log = LoggerFactory.getLogger(SupernaturalAbilityImpl.class);

    private final String name = "蜘蛛侠";

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
