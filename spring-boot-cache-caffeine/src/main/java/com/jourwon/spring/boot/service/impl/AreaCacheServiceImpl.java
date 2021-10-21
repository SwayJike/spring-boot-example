package com.jourwon.spring.boot.service.impl;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.jourwon.spring.boot.model.entity.Area;
import com.jourwon.spring.boot.service.AreaCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 行政区划缓存
 *
 * @author JourWon
 * @date 2021/10/21
 */
@Slf4j
@Service
public class AreaCacheServiceImpl implements AreaCacheService, InitializingBean {

    /**
     * 是否开始本地缓存
     */
    @Value("${area.cache.open:true}")
    private boolean areaCacheOpen;

    /**
     * 本地缓存过期时间,单位:秒
     */
    @Value("${area.cache.expire:120}")
    private long areaCacheExpire;

    /**
     * 本地缓存刷新时间,单位:秒
     */
    @Value("${area.cache.refresh:60}")
    private long areaCacheRefresh;

    /**
     * key:行政区划编码,value:行政区划对象
     */
    private LoadingCache<String, Area> areaCache;

    @Override
    public void afterPropertiesSet() {
        if (!areaCacheOpen) {
            return;
        }
        this.areaCache = Caffeine.newBuilder()
                .expireAfterWrite(areaCacheExpire, TimeUnit.SECONDS)
                .refreshAfterWrite(areaCacheRefresh, TimeUnit.SECONDS)
                .initialCapacity(10)
                .maximumSize(100)
                .build(this::getAreaFromRedis);
    }

    @Override
    public Area getAreaInfo(String areaCode) {
        if (StringUtils.isBlank(areaCode)) {
            log.warn("获取行政区划的时候,传入的行政区划为空,直接返回null");
            return null;
        }

        if (areaCacheOpen) {
            return areaCache.get(areaCode);
        }
        return getAreaFromRedis(areaCode);
    }

    private Area getAreaFromRedis(String orgCode) {
        // 生产环境可以从redis中获取，这里直接创建返回
        return new Area("广东省广州市", "广州市", orgCode);
    }

}
