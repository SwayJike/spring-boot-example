package com.jourwon.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 *
 * @author JourWon
 * @date 2021/7/25
 */
@Slf4j
@RestController
@RefreshScope
public class HelloWorldController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${extension-config}")
    private String extensionConfig;

    @Value("${shared-config}")
    private String sharedConfig;

    @GetMapping("/hello_world")
    public String helloWorld() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "应用名称:" + applicationName + " 扩展配置:" + extensionConfig + " 共享配置:" + sharedConfig;
    }

}
