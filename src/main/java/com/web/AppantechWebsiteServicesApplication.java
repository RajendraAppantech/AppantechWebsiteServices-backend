package com.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class AppantechWebsiteServicesApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(AppantechWebsiteServicesApplication.class, args);
		System.out.println("Server Start...");
	}
}
