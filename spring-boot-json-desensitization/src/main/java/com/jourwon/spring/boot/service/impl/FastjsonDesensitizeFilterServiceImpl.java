package com.jourwon.spring.boot.service.impl;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.jourwon.spring.boot.annotation.Desensitization;
import com.jourwon.spring.boot.enums.DesensitionTypeEnum;
import com.jourwon.spring.boot.service.DesensitizeService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 使用fastjson进行数据脱敏
 *
 * @author JourWon
 * @date 2021/2/12
 */
@Slf4j
public class FastjsonDesensitizeFilterServiceImpl implements ValueFilter, DesensitizeService {

    @Override
    public Object process(Object object, String name, Object value) {
        if (!(value instanceof String) || ((String) value).length() == 0) {
            return value;
        }
        try {
            Field field = object.getClass().getDeclaredField(name);
            Desensitization desensitization;
            if (String.class != field.getType() || (desensitization = field.getAnnotation(Desensitization.class)) == null) {
                return value;
            }
            DesensitionTypeEnum type = desensitization.type();
            List<String> regular = this.desensitize(type, desensitization);
            if (regular.size() > 1) {
                String match = regular.get(0);
                String result = regular.get(1);
                if (null != match && result != null && match.length() > 0) {
                    return ((String) value).replaceAll(match, result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
