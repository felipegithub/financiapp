package br.com.financiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableEurekaClient
public class FinanciappGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanciappGatewayApplication.class, args);
	}

}
