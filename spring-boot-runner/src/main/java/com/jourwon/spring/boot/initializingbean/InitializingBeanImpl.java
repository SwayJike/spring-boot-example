package com.jourwon.spring.boot.initializingbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 实现InitializingBean接口
 *
 * @author JourWon
 * @date 2021/12/4
 */
@Slf4j
@Component
public class InitializingBeanImpl implements InitializingBean {

    public InitializingBeanImpl() {
        log.info("InitializingBeanImpl Constructor构造器");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBeanImpl afterPropertiesSet方法");
    }

}
