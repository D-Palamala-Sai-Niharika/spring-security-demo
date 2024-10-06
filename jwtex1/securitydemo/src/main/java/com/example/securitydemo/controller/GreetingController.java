package com.example.securitydemo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securitydemo.model.User;
import com.example.securitydemo.service.UserService;

@RestController("/")
public class GreetingController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/home")
	public String greetHello() {
		return "Hello";
	}
	
	@GetMapping("/user/getUser")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
	
	@GetMapping("/admin/getUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}
}
