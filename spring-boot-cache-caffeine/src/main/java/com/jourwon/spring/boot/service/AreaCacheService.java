package com.jourwon.spring.boot.service;

import com.jourwon.spring.boot.model.entity.Area;

/**
 * 行政区划缓存接口
 *
 * @author JourWon
 * @date 2021/10/21
 */
public interface AreaCacheService {

    /**
     * 根据行政区划编码获取行政区划对象
     *
     * @param areaCode 行政区划编码
     * @return Area 行政区划对象
     */
    Area getAreaInfo(String areaCode);

}
