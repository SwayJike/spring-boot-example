package com.jourwon.spring.boot.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger3配置
 * EnableOpenApi注解:开启Swagger自定义接口文档
 *
 * @author JourWon
 * @date 2021/1/21
 */
@EnableOpenApi
@Configuration
public class Swagger3Config {

    /**
     * 创建Docket对象
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * API基础信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3-API接口文档")
                .description("API接口文档")
                .contact(new Contact("JourWon", "https://thinkwon.blog.csdn.net/", "JourWon@163.com"))
                .version("1.0.0")
                .build();
    }

}
