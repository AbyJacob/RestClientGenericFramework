package com.sedai.sops.secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;


@EnableAutoConfiguration
@SpringBootApplication
/*
@author  : Aby.Jacob
@task    : SOps RestClientGenericFramework
@remarks : Spring Boot  + PostgreSQL  + Spring Data JPA  + Swagger 
*/
@EnableRetry
public class SecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureApplication.class, args);
	}

}
