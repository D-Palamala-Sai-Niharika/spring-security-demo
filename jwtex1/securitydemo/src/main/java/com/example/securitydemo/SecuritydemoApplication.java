package com.example.securitydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuritydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritydemoApplication.class, args);
	}
	
//	404 - NOT_FOUND - Ex:ResourceNotFound
//	201 - CREATED 
//	200 - OK
//	404 - Unauthorized access - unknown user(not in db/userdetailsservice)
//	403 - Forbidden - Access Denied - Authenticated but not Authorized

}
