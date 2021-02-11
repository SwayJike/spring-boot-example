package com.jourwon.spring.boot.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 应用配置
 *
 * @author JourWon
 * @date 2021/2/11
 */
@Data
@ConfigurationProperties(prefix = "spring.application")
public class ApplicationConfigurationProperties {

    /**
     * 应用名称
     */
    private String name;

}
