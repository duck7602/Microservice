package com.ecommerce.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceInventoryServiceApplication.class, args);
	}

}
