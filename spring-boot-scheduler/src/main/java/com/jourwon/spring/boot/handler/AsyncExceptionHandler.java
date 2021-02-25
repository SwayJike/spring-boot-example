package com.jourwon.spring.boot.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 异步异常处理类
 *
 * @author JourWon
 * @date 2021/2/23
 */
@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        log.info("Exception message - {}", throwable.getMessage());
        log.info("Method name - {}", method.getName());
        for (Object param : objects) {
            log.info("Parameter value - {}", param);
        }
    }

}
