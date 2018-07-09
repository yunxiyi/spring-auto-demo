package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan({"com.finup"})
public class DemoApplication {

	public static void main(String[] args) {
        System.setProperty("java.net.preferIPv4Stack", "true");
		SpringApplication.run(DemoApplication.class, args);
	}
}
