package com.jourwon.spring.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.async.AsyncLoggerContextSelector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志controller
 *
 * @author JourWon
 * @date 2021/2/11
 */
@Slf4j
@RestController
@Api(tags = "日志")
public class LogController {

    @GetMapping("/log")
    @ApiOperation("打印日志")
    public void log() {
        log.info("是否为异步日志：{}", AsyncLoggerContextSelector.isSelected());
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

}
