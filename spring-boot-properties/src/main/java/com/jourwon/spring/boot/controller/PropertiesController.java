package com.jourwon.spring.boot.controller;

import com.jourwon.spring.boot.prop.ApplicationConfigurationProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 加载配置文件controller
 *
 * @author JourWon
 * @date 2021/2/11
 */
@RestController
@Api(tags = "加载配置文件")
@RequestMapping("/prop")
@Configuration
@EnableConfigurationProperties(ApplicationConfigurationProperties.class)
public class PropertiesController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private Environment env;

    @Resource
    private ApplicationConfigurationProperties configProps;


    @GetMapping("/applicationName1")
    @ApiOperation("应用名称1")
    public String applicationName1() {
        return applicationName;
    }

    @GetMapping("/applicationName2")
    @ApiOperation("应用名称2")
    public String applicationName2() {
        return env.getProperty("spring.application.name");
    }

    @GetMapping("/applicationName3")
    @ApiOperation("应用名称3")
    public String applicationName3() {
        return configProps.getName();
    }

}
