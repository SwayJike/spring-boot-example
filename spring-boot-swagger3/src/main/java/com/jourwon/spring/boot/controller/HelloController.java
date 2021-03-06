package com.jourwon.spring.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author JourWon
 * @date 2021/1/21
 */
@RestController
@RequestMapping("/hello")
@Api(tags = "【示例】你好")
public class HelloController {

    @GetMapping("/say_hi")
    @ApiOperation(value = "向客人问好")
    public ResponseEntity<String> sayHi(@RequestParam("name") String name) {
        return ResponseEntity.ok("Hi:" + name);
    }

}
