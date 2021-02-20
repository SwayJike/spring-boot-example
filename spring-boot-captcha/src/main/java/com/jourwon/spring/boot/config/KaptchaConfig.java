package com.jourwon.spring.boot.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码配置
 *
 * @author JourWon
 * @date 2021/2/20
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        // 图片边框,合法值:yes,no,默认yes
        properties.put("kaptcha.border", "no");
        // 图片宽,默认200
        properties.setProperty("kaptcha.image.width", "120");
        // 图片高,默认50
        properties.setProperty("kaptcha.image.height", "40");
        // 文本集合,验证码值从此集合中获取,默认abcde2345678gfynmnpwx
        properties.setProperty("kaptcha.textproducer.char.string", "23456789abcdefghkmnpqrstuvwxyz");
        // 验证码长度,默认5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 字体,默认Arial,Courier
        properties.put("kaptcha.textproducer.font.names", "Arial,Courier,宋体,楷体,微软雅黑");
        // 字体大小,默认40
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        // 字体颜色,默认black
        properties.put("kaptcha.textproducer.font.color", "black");
        // 文字间隔,默认2
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        // 干扰颜色,默认black
        properties.setProperty("kaptcha.noise.color", "black");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
