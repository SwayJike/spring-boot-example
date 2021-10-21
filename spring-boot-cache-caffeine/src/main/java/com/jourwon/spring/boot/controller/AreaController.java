package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.model.entity.Area;
import com.jourwon.spring.boot.service.AreaCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 行政区划 controller
 *
 * @author JourWon
 * @date 2021/10/21
 */
@RestController
@RequestMapping("/area")
@Api(tags = "行政区划")
public class AreaController {

    @Resource
    private AreaCacheService areaCacheService;

    @PostMapping
    @ApiOperation("根据行政区划编码获取行政区划对象")
    public Area insertSelective(@RequestParam String areaCode) {
        return areaCacheService.getAreaInfo(areaCode);
    }

}
