package com.jourwon.spring.boot.config;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域资源共享
 *
 * @author JourWon
 * @date 2021/1/24
 */
// @Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                // SpringBoot2.4.0 [allowedOriginPatterns]代替[allowedOrigins]
                .allowedOriginPatterns(CorsConfiguration.ALL)
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .maxAge(7200);
    }

}
