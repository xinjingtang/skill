package com.group.integrate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.group.integrate.mapper")
@EnableCaching
@EnableAsync
public class IntegrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrateApplication.class, args);
	}
}
