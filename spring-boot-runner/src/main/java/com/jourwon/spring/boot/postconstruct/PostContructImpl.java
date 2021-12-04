package com.jourwon.spring.boot.postconstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * PostConstruct注解
 *
 * @author JourWon
 * @date 2021/12/4
 */
@Slf4j
@Component
public class PostContructImpl {

    public PostContructImpl() {
        log.info("PostContructImpl构造器");
    }

    @PostConstruct
    public void runAfterObjectCreated() {
        log.info("PostContruct runAfterObjectCreated方法");
    }

}
