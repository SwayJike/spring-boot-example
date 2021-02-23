package com.jourwon.spring.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author JourWon
 * @date 2021/2/23
 */
@Slf4j
@Service
public class AsyncService {

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    public void asyncInvokeSimplest() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncSimplest");
    }

    /**
     * 带参数的异步调用
     *
     * @param s 参数
     */
    @Async
    public void asyncInvokeWithParameter(String s) {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncInvokeWithParameter, parementer={}", s);
    }

    /**
     * 异常调用返回Future
     *
     * @param i 参数
     * @return Future<String>
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        log.info("asyncInvokeReturnFuture, parementer={}", i);
        Future<String> future;
        try {
            Thread.sleep(1000 * i);
            future = new AsyncResult<>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<>("error");
        }
        return future;
    }

    /**
     * 带参数的异步调用
     * 对于返回值是void，异常会被AsyncUncaughtExceptionHandler处理掉
     *
     * @param s 参数
     */
    @Async
    public void asyncInvokeWithException(String s) {
        log.info("asyncInvokeWithException, parementer={}", s);
        throw new IllegalArgumentException(s);
    }

    /**
     * 异常调用返回Future
     * 对于返回值是Future，不会被AsyncUncaughtExceptionHandler处理，需要我们在方法中捕获异常并处理
     * 或者在调用方在调用Futrue.get时捕获异常进行处理
     *
     * @param i 参数
     * @return Future<String>
     */
    @Async
    public Future<String> asyncInvokeReturnFutureWithException(int i) {
        log.info("asyncInvokeReturnFutureWithException, parementer={}", i);
        Future<String> future;
        try {
            Thread.sleep(1000 * i);
            future = new AsyncResult<>("success:" + i);
            throw new IllegalArgumentException("a");
        } catch (InterruptedException e) {
            future = new AsyncResult<>("InterruptedException");
            log.error("InterruptedException");
        } catch (IllegalArgumentException e) {
            future = new AsyncResult<>("IllegalArgumentException");
            log.error("IllegalArgumentException");
        }
        return future;
    }

}
