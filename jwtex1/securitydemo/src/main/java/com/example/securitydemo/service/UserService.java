package com.example.securitydemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.example.securitydemo.model.User;

@Service
public class UserService {
	
	List<User> store=new ArrayList<User>();
	
	public UserService(){
		store.add(new User(UUID.randomUUID().toString(), "pinky", "pinky@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "ponky", "ponky@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "donkey", "donkey@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "rinky", "rinky@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "test", "test@gmail.com"));
	}
	
	public List<User> getUsers(){
		return store;
	}
	
}
