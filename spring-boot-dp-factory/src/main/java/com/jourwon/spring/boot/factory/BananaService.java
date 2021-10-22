package com.jourwon.spring.boot.factory;

import com.jourwon.spring.boot.enums.FruitEnum;
import com.jourwon.spring.boot.model.dto.Banana;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 香蕉 service
 *
 * @author JourWon
 * @date 2021/10/23
 */
@Service
public class BananaService implements Fruit<Banana> {

    // 这里可以注入各种mapper

    @Override
    public Integer getType() {
        return FruitEnum.BANANA.getType();
    }

    @Override
    public List<Banana> list(int number) {
        List<Banana> list = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {
            list.add(new Banana("广东香蕉", 3));
        }

        return list;
    }

}
