package com.jourwon.spring.boot.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jourwon.spring.boot.model.vo.UserVO;
import com.jourwon.spring.boot.service.UserCacheService;
import com.jourwon.spring.boot.util.ScheduleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用户缓存(动态定时任务)
 *
 * @author JourWon
 * @date 2021/10/17
 */
@Slf4j
@Service
public class UserCacheServiceImpl implements UserCacheService {

    /**
     * 用户缓存cron表达式,每隔20s执行一次方法
     */
    @Value("${schedule.cron.userCache:*/20 * * * * ?}")
    private String userCacheCron;

    private static final ReadWriteLock USER_CACHE_LOCK = new ReentrantReadWriteLock();
    private static final Lock USER_CACHE_WRITE_LOCK = USER_CACHE_LOCK.writeLock();
    private static final Lock USER_CACHE_READ_LOCK = USER_CACHE_LOCK.readLock();

    @Override
    public void execute() {
        USER_CACHE_WRITE_LOCK.lock();
        try {
            log.info("开始缓存用户信息,用户缓存cron表达式:{}", userCacheCron);

            // 这里直接添加用户,实际开发中有以下两种做法:
            // 1.可以从数据库中获取然后缓存到本地缓存
            // 2.可以从数据库中获取然后缓存到Redis,最终从Redis中获取缓存
            List<UserVO> list = new ArrayList<UserVO>(16) {{
                add(new UserVO(1L, "张三"));
                add(new UserVO(2L, "李四"));
                add(new UserVO(3L, "王五"));
            }};
            log.info("缓存用户信息成功,用户信息:{}", new ObjectMapper().writeValueAsString(list));

            if (CollectionUtils.isEmpty(list)) {
                log.warn("结束缓存用户信息,用户信息为空!");
                return;
            }

            log.info("结束缓存用户信息...");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            USER_CACHE_WRITE_LOCK.unlock();
        }
    }

    @Override
    public void afterPropertiesSet() {
        log.info("UserCacheServiceImpl afterPropertiesSet method...");
        ScheduleManager.addSchedule(userCacheCron, this);
    }

}
