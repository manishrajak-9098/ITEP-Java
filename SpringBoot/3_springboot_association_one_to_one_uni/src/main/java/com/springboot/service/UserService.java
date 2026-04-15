package com.springboot.service;

import java.util.List;

import com.springboot.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public List<User> getAllUser();
	
	public User getUser(int id);
	
	
}
