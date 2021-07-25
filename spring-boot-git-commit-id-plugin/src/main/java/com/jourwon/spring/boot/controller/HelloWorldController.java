package com.jourwon.spring.boot.controller;

import lombok.extern.slf4j.Slf4j;
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
public class HelloWorldController {

    @GetMapping("/hello_world")
    public String helloWorld() {
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
        return "Hello World";
    }

}
