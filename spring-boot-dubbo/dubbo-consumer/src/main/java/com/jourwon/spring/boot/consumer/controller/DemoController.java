package com.jourwon.spring.boot.consumer.controller;

import com.jourwon.spring.boot.consumer.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller
 *
 * @author JourWon
 * @date 2021/3/1
 */
@RestController
public class DemoController {

    @DubboReference(version = "${demo.service.version}")
    private DemoService demoService;

    @GetMapping("/demo")
    public String demo() {
        return demoService.sayHello("mercyblitz");
    }

}
