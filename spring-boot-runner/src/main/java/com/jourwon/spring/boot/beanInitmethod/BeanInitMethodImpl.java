package com.jourwon.spring.boot.beanInitmethod;

import lombok.extern.slf4j.Slf4j;

/**
 * @author JourWon
 * @date 2021/12/4
 */
@Slf4j
public class BeanInitMethodImpl {

    public void runAfterObjectCreated() {
        log.info("BeanInitMethodImpl runAfterObjectCreated方法,yooooooooo......... someone called me");
    }

}
