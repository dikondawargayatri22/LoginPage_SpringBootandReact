package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="com.nt")
@EntityScan("com.nt.entity")
@EnableJpaRepositories("com.nt.Repository")
public class UserLoginPage1RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginPage1RestApiApplication.class, args);
	}

}
