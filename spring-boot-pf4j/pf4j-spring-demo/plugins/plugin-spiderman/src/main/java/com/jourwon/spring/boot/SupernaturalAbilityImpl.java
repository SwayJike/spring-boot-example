package com.jourwon.spring.boot;

import lombok.extern.slf4j.Slf4j;

/**
 * 超能力 实现类
 *
 * @author JourWon
 * @date 2021/9/14
 */
@Slf4j
public class SupernaturalAbilityImpl implements SupernaturalAbility {

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
