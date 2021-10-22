package com.jourwon.spring.boot.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.jourwon.spring.boot.factory.Fruit;
import com.jourwon.spring.boot.factory.FruitSelector;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 水果 controller
 *
 * @author JourWon
 * @date 2021/10/23
 */
@Api(tags = "水果")
@ApiSupport(author = "JourWon@163.com")
@RestController
@RequestMapping("/fruit")
public class FruitController {

    @Resource
    private FruitSelector fruitSelector;

    @GetMapping("/{fruitType}")
    @ApiOperation("根据水果类型获取水果列表")
    public String get(@PathVariable("fruitType") Integer fruitType) {
        Fruit fruit = fruitSelector.getFruit(fruitType);
        List list = fruit.list(fruitType);

        return list.toString();
    }

}
