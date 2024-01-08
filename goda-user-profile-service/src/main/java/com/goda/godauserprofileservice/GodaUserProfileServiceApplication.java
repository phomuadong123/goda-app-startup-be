package com.goda.godauserprofileservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient

@RestController
public class GodaUserProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodaUserProfileServiceApplication.class, args);
	}
 @GetMapping("/api/users/test")
    public String testUserProfileService() {
        return "User Profile Service is up and running!";
    }
}
