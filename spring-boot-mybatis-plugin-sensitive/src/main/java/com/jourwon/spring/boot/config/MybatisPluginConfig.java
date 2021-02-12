package com.jourwon.spring.boot.config;

import com.jourwon.spring.boot.plugin.SensitivePlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis插件配置
 *
 * @author JourWon
 * @date 2021/2/12
 */
@Configuration
public class MybatisPluginConfig {

    @Bean
    public SensitivePlugin sensitivePlugin(){
        return new SensitivePlugin();
    }

}
