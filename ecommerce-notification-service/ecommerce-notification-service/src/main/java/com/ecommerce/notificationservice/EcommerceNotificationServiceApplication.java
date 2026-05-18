package com.ecommerce.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommerceNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceNotificationServiceApplication.class, args);
	}

}
