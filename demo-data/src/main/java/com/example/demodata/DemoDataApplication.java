package com.example.demodata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDataApplication.class, args);
	}
}
