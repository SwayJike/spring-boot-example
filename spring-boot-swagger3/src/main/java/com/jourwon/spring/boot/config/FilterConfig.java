package com.jourwon.spring.boot.config;

import com.jourwon.spring.boot.filter.HttpLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 *
 * @author JourWon
 * @date 2021/1/22
 */
@Configuration
public class FilterConfig {

    /**
     * 日志过滤器
     *
     * @return FilterRegistrationBean 过滤器
     */
    @Bean
    public FilterRegistrationBean<HttpLoggingFilter> httpLoggingFilterRegistration() {
        FilterRegistrationBean<HttpLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpLoggingFilter());
        // 拦截路径
        registration.addUrlPatterns("/*");
        // 拦截器名称
        registration.setName("httpLoggingFilter");
        // 顺序
        registration.setOrder(100);
        return registration;
    }

}