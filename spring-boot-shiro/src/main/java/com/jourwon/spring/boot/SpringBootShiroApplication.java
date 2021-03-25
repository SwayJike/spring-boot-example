package com.jourwon.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *
 * @author JourWon
 * @date 2021/3/25
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jourwon.spring.boot.**.mapper")
public class SpringBootShiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootShiroApplication.class, args);
	}

}
