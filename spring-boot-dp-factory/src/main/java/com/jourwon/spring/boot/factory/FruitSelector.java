package com.jourwon.spring.boot.factory;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 水果选择器
 *
 * @author JourWon
 * @date 2021/10/23
 */
@Component
public class FruitSelector implements InitializingBean {

    @Resource
    private List<Fruit> fruitList;

    private Map<Integer, Fruit> fruitMap;

    public Fruit getFruit(Integer type) {
        return fruitMap.get(type);
    }

    @Override
    public void afterPropertiesSet() {
        fruitMap = fruitList.stream().collect(Collectors.toMap(Fruit::getType, Function.identity()));
    }

}
