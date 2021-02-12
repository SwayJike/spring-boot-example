package com.jourwon.spring.boot.plugin;

import java.util.function.Function;

/**
 * 脱敏策略函数
 *
 * @author JourWon
 * @date 2021/2/12
 */
public interface Desensitizer extends Function<String,String> {
}
