package com.ecommerce.promotionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EcommercePromotionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommercePromotionServiceApplication.class, args);
	}

}
