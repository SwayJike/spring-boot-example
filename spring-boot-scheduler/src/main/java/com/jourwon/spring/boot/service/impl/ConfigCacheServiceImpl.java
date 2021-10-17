package com.jourwon.spring.boot.service.impl;

import com.jourwon.spring.boot.service.ConfigCacheService;
import com.jourwon.spring.boot.util.ScheduleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 配置缓存(动态定时任务)
 *
 * @author JourWon
 * @date 2021/10/17
 */
@Slf4j
@Service
public class ConfigCacheServiceImpl implements ConfigCacheService {

    /**
     * 配置缓存cron表达式,每隔30s执行一次方法
     */
    @Value("${schedule.cron.configCache:*/30 * * * * ?}")
    private String configCacheCron;

    private static final ReadWriteLock CONFIG_CACHE_LOCK = new ReentrantReadWriteLock();
    private static final Lock CONFIG_CACHE_WRITE_LOCK = CONFIG_CACHE_LOCK.writeLock();
    private static final Lock CONFIG_CACHE_READ_LOCK = CONFIG_CACHE_LOCK.readLock();

    @Override
    public void execute() {
        CONFIG_CACHE_WRITE_LOCK.lock();
        try {
            log.info("开始缓存配置信息,配置缓存cron表达式:{}", configCacheCron);

            // 这里直接添加配置,实际开发中有以下两种做法:
            // 1.可以从数据库中获取然后缓存到本地缓存
            // 2.可以从数据库中获取然后缓存到Redis,最终从Redis中获取缓存
            log.info("缓存配置信息成功...");

            log.info("结束缓存配置信息...");
        } finally {
            CONFIG_CACHE_WRITE_LOCK.unlock();
        }
    }

    @Override
    public void afterPropertiesSet() {
        log.info("ConfigCacheServiceImpl afterPropertiesSet method...");
        ScheduleManager.addSchedule(configCacheCron, this);
    }

}
