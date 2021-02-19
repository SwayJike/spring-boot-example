package com.jourwon.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorldController
 *
 * @author JourWon
 * @date 2021/1/15
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello_world")
    public String helloWorld() {
        return "Hello World";
    }

}
