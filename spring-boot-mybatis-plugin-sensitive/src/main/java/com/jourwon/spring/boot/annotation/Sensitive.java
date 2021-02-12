package com.jourwon.spring.boot.annotation;

import com.jourwon.spring.boot.enums.SensitiveStrategyEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏字段的标记注解
 *
 * @author JourWon
 * @date 2021/2/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Sensitive {

    SensitiveStrategyEnum strategy();

}
