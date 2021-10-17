package com.jourwon.spring.boot.util;

import com.jourwon.spring.boot.model.dto.ScheduleInfo;
import com.jourwon.spring.boot.service.CacheService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务管理器
 *
 * @author JourWon
 * @date 2021/10/17
 */
@Slf4j
public class ScheduleManager {

    /**
     * 定时任务缓存,key:CacheService实现类类名,value:定时任务信息
     */
    private static final Map<String, ScheduleInfo> SCHEDULE_INFO_MAP = new ConcurrentHashMap<>(16);

    /**
     * 添加定时任务
     *
     * @param cron         cron表达式
     * @param cacheService 缓存接口
     */
    public static void addSchedule(String cron, CacheService cacheService) {
        String clazzName = cacheService.getClass().getSimpleName();
        if (SCHEDULE_INFO_MAP.containsKey(clazzName)) {
            return;
        }

        SCHEDULE_INFO_MAP.put(clazzName, new ScheduleInfo(clazzName, cron, cacheService::execute));
    }

    /**
     * 定时任务列表
     *
     * @return Collection<ScheduleInfo> 定时任务列表
     */
    public static Collection<ScheduleInfo> scheduleList() {
        return SCHEDULE_INFO_MAP.values();
    }

}
