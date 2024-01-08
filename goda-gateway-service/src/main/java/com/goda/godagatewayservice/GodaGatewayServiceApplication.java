package com.goda.godagatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GodaGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodaGatewayServiceApplication.class, args);
	}
}
