package com.jourwon.spring.boot.factory;

import com.jourwon.spring.boot.enums.FruitEnum;
import com.jourwon.spring.boot.model.dto.Apple;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 苹果 service
 *
 * @author JourWon
 * @date 2021/10/23
 */
@Service
public class AppleService implements Fruit<Apple> {

    // 这里可以注入各种mapper

    @Override
    public Integer getType() {
        return FruitEnum.APPLE.getType();
    }

    @Override
    public List<Apple> list(int number) {
        List<Apple> list = new ArrayList<>(number);

        for (int i = 0; i < number; i++) {
            list.add(new Apple("陕西苹果", 8));
        }

        return list;
    }

}
