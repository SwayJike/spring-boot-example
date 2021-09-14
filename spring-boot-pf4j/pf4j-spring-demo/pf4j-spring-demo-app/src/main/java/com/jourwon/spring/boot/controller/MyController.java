package com.jourwon.spring.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.pf4j.demo.api.Hero;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 插件接口测试
 *
 * @author JourWon
 * @date 2021/9/14
 */
@RestController
@RequestMapping("/test")
@Api(tags = "插件接口测试")
public class MyController {

    @Resource
    private SpringPluginManager pluginManager;

    @GetMapping("/invoke")
    @ApiOperation(value = "执行英雄接口方法,调用所有Hero接口实现类的方法")
    public String invoke() {
        List<Hero> heroList = pluginManager.getExtensions(Hero.class);
        for (Hero hero : heroList) {
            for (Method method : hero.getClass().getDeclaredMethods()) {
                try {
                    method.setAccessible(true);
                    method.invoke(hero, null);
                } catch (Exception e) {

                }
            }
        }
        return "";
    }

}
