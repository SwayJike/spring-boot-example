package com.jourwon.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

/**
 * 跨域资源共享
 *
 * @author JourWon
 * @date 2021/1/24
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何域名进行跨域访问,SpringBoot2.4.0可以如下配置,
        // 或使用allowedOriginPatterns,如使用corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL)代码;
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList(CorsConfiguration.ALL));
        // 允许凭证,允许跨域发送cookie
        corsConfiguration.setAllowCredentials(true);
        // 允许任何请求头
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 允许任何方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
