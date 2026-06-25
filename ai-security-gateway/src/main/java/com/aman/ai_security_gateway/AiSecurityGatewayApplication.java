package com.aman.ai_security_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AiSecurityGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiSecurityGatewayApplication.class, args);
	}

}
