package com.gladson.seletivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.gladson.seletivo")
public class SeletivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeletivoApplication.class, args);
	}

}
