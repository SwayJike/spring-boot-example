package com.jourwon.spring.boot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ObjectMapper工具类
 *
 * @author JourWon
 * @date 2021/1/27
 */
@Component
public class ObjectMapperUtils {

    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        ObjectMapperUtils.objectMapper = objectMapper;
    }

    public static String objToJson(Object object) {
        // ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static <T> T jsonToObj(String json, Class<T> clazz) {
        // ObjectMapper objectMapper = new ObjectMapper();
        T result = null;
        try {
            result = objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> Map<String, T> jsonToMap(String json) {
        // ObjectMapper objectMapper = new ObjectMapper();
        Map<String, T> map = null;
        try {
            map = objectMapper.readValue(json, new TypeReference<Map<String, T>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    public <T> List<T> jsonToList(String json) {
        // ObjectMapper objectMapper = new ObjectMapper();
        List<T> list = null;
        try {
            list = objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }

}
