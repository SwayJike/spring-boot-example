package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.annotation.RateLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author JourWon
 * @date 2021/2/18
 */
@Slf4j
@RestController
@Api(tags = "HelloController")
public class HelloController {

    @RateLimiter(value = 1, timeout = 2000)
    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello() {
        log.info("【hello】被执行了...");
        return "hello";
    }

    @GetMapping("/hello_world")
    @ApiOperation("hello_world")
    public String helloWorld() {
        return "Hello World";
    }

}
