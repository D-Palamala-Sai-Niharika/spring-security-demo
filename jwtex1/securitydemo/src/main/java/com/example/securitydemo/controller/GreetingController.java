package com.example.securitydemo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.securitydemo.model.User;
import com.example.securitydemo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GreetingController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/home")
	public String greetHello(HttpServletRequest request, HttpServletResponse response) {
		//HttpServletRequest, HttpServletResponse objects can be used for every controllers request to get or modify request and response information or headers
		return "Hello";
	}
	
	//@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/user/getUser")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/getUsers")
	public List<User> getUsers() {
		return userService.getUsers();
	}
}
