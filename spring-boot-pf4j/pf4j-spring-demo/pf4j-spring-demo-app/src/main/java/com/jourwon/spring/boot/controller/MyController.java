package com.jourwon.spring.boot.controller;

import org.pf4j.demo.api.Hero;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/test")
public class MyController {

    @Autowired
    private SpringPluginManager springPluginManager;

    @GetMapping("/get")
    public String getController() {
        List<Hero> heroList = springPluginManager.getExtensions(Hero.class);
        StringBuilder builder = new StringBuilder();
        for (Hero hero : heroList) {
            for (Method method : hero.getClass().getDeclaredMethods()) {
                try {
                    method.setAccessible(true);
                    Object invoke = method.invoke(hero, null);
                } catch (Exception e) {

                }
            }
        }
        return "";
    }

    @GetMapping("/getSpiderman")
    public String getSpiderman() throws InvocationTargetException, IllegalAccessException {
        Object bean = springPluginManager.getApplicationContext().getBean("spidermanPlugin");
        for (Method method : bean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            method.invoke(bean,null);
        }
        return bean.toString();
    }
}
