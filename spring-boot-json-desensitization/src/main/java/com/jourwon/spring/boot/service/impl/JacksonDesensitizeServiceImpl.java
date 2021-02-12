package com.jourwon.spring.boot.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.jourwon.spring.boot.annotation.Desensitization;
import com.jourwon.spring.boot.enums.DesensitionTypeEnum;
import com.jourwon.spring.boot.service.DesensitizeService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 使用Jackson进行数据脱敏
 *
 * @author JourWon
 * @date 2021/2/12
 */
@Slf4j
public class JacksonDesensitizeServiceImpl extends JsonSerializer<String> implements ContextualSerializer, DesensitizeService {

    private DesensitionTypeEnum type;

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (type != null) {
            try {
                List<String> regular = this.desensitize(type, null);
                if (regular.size() > 1) {
                    String match = regular.get(0);
                    String result = regular.get(1);
                    if (null != match && result != null && match.length() > 0) {
                        jsonGenerator.writeString(value.replaceAll(match, result));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        type = beanProperty.getAnnotation(Desensitization.class).type();
        return this;
    }

}
