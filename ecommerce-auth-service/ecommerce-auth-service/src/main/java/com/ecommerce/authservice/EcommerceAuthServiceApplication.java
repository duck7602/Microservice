package com.ecommerce.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAuthServiceApplication.class, args);
	}

}
