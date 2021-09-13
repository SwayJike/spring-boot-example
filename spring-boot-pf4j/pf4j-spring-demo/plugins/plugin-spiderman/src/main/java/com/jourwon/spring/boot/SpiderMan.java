package com.jourwon.spring.boot;

import org.pf4j.Extension;
import org.pf4j.demo.api.Hero;
import org.springframework.beans.factory.annotation.Autowired;

@Extension
public class SpiderMan implements Hero {

    public SpiderMan() {
    }

    @Autowired
    private SupernaturalAbility supernaturalAbility;

    @Override
    public void eat() {
        supernaturalAbility.eat();
    }

    @Override
    public void say() {
        supernaturalAbility.say();
    }

    @Override
    public void walk() {
        supernaturalAbility.walk();
    }

    @Override
    public void run() {
        supernaturalAbility.run();
    }

    @Override
    public void attack() {
        supernaturalAbility.attack();
    }

    @Override
    public void defense() {
        supernaturalAbility.defense();
    }
}
