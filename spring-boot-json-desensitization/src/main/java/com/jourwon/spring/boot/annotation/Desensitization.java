package com.jourwon.spring.boot.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jourwon.spring.boot.enums.DesensitionTypeEnum;
import com.jourwon.spring.boot.service.impl.JacksonDesensitizeServiceImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏注解
 *
 * @author JourWon
 * @date 2021/2/12
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JsonSerialize(using = JacksonDesensitizeServiceImpl.class)
@JacksonAnnotationsInside
public @interface Desensitization {

    /**
     * 脱敏规则类型
     *
     * @return DesensitionTypeEnum
     */
    DesensitionTypeEnum type();

    /**
     * 附加值, 自定义正则表达式等
     *
     * @return String[]
     */
    String[] attach() default "";

}
