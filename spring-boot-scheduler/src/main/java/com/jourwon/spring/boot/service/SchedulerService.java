package com.jourwon.spring.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * SchedulerService
 *
 * @author JourWon
 * @date 2021/2/23
 */
@Slf4j
@Service
public class SchedulerService {

    /**
     * 每次方法执行完毕后，等待3s再执行此方法。
     * 同时只能有1个线程运行此方法
     */
    @Scheduled(fixedDelay = 3000)
    public void fixedDelay() {
        log.info("fixedDelay-- 线程 : " + Thread.currentThread().getName());
    }

    /**
     * 每隔3s调用一次此方法，无论之前的方法是否执行完毕
     * 同时可能有N个线程执行此方法
     */
    @Scheduled(fixedRate = 3000)
    public void fixedRate() {
        log.info("fixedRate-- 线程 : " + Thread.currentThread().getName());
    }

    /**
     * initialDelay:第一次调用此方法前的等待时间,然后每隔3s调用一次此方法
     */
    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void initialDelayAndfixedRate() {
        log.info("initialDelayAndfixedRate-- 线程 : " + Thread.currentThread().getName());
    }

    /**
     * 支持cron语法：
     * 每个参数的意义分别是： second, minute, hour, day of month, month, day of week
     * <p>
     * 如下：每隔3s执行一次方法
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void cron() {
        log.info("cron-- 线程 : " + Thread.currentThread().getName());
    }

    /**
     * 多线程定时任务
     */
    @Async
    @Scheduled(fixedDelay = 3000)
    public void asyncFixedDelay() {
        log.info("asyncFixedDelay-- 线程 : " + Thread.currentThread().getName());
    }

}
