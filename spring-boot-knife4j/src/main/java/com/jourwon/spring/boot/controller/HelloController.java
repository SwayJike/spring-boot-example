package com.jourwon.spring.boot.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
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
@Api(tags = "你好")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperationSupport(author = "JourWon@163.com")
    @ApiOperation(value = "向客人问好")
    @GetMapping("/say_hi")
    public ResponseEntity<String> sayHi(@RequestParam("name") String name) {
        return ResponseEntity.ok("Hi:" + name);
    }

}
