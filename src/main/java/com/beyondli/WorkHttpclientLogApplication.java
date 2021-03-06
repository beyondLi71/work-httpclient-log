package com.beyondli;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.beyondli.repository")
public class WorkHttpclientLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkHttpclientLogApplication.class, args);
	}
}
