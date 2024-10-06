package com.example.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		//when you add security the user details are fetched from inbuilt UserDetailsService and you can customize it with customUserDetailsService that implements UserDetailsService.Users list taken from this service to authenticate 
		User user1=(User) User.builder().username("neha").password(passwordEncoder().encode("neha")).roles("ADMIN","USER").build();
		User user2=(User) User.builder().username("ishan").password(passwordEncoder().encode("ishan")).roles("USER").build();
		return new InMemoryUserDetailsManager(user1,user2);
	}
	
}
