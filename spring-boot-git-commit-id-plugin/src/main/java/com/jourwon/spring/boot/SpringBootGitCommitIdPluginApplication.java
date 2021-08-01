package com.jourwon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author JourWon
 * @date 2021/7/25
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootGitCommitIdPluginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGitCommitIdPluginApplication.class, args);
    }

}
