package com.example.securitydemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.example.securitydemo.security.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf->csrf.disable()) // Disable CSRF for simplicity; lambda expressions
	            .authorizeHttpRequests(auth -> auth
	            	.requestMatchers("/h2-console/**").permitAll()
	                .requestMatchers("/admin/**").hasRole("ADMIN")
	                .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
	                .anyRequest().authenticated() // All other requests require authentication or you can use @PreAuthorize(hasRole('USER')) above controller method enabling @EnableMethodSecurity
	            )
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No session will be created or used
	            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)) // Set custom entry point .if used you won't get browser alert for login only postman
	            .headers(header->header.frameOptions(frameOption->frameOption.sameOrigin()))
	            .httpBasic(Customizer.withDefaults()); // Enable basic authentication
	        return http.build();
	 }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		//when you add security the user details are fetched from inbuilt UserDetailsService and you can customize it with customUserDetailsService that implements UserDetailsService.Users list taken from this service to authenticate 
		UserDetails user1= User.builder().username("neha").password(passwordEncoder().encode("neha")).roles("ADMIN","USER").build();
		UserDetails user2=User.builder().username("ishan").password(passwordEncoder().encode("ishan")).roles("USER").build();
		JdbcUserDetailsManager userDetailsManager=new JdbcUserDetailsManager(dataSource);
		userDetailsManager.createUser(user1);
		userDetailsManager.createUser(user2);
		//return new InMemoryUserDetailsManager(user1,user2);
		return userDetailsManager;
	}
	
}
