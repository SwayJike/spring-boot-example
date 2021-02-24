package com.jourwon.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * 
 * @author JourWon
 * @date 2021/2/24
 */
@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSchedulerApplication.class, args);
	}

}
