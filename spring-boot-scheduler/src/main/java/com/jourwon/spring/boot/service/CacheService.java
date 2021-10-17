package com.jourwon.spring.boot.service;

import org.springframework.beans.factory.InitializingBean;

/**
 * 缓存接口
 *
 * @author JourWon
 * @date 2021/10/17
 */
public interface CacheService extends InitializingBean {

    /**
     * 执行方法
     */
    void execute();

}
