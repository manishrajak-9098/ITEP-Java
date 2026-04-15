package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.User;
import com.springboot.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {
	
	
	 @Autowired
	    private UserDao repo;
	 	
	 @Override
	    public User saveUser(User user) {
	        return repo.save(user);
	    }
	 @Override
	    public List<User> getAllUser() {
	        return repo.findAll();
	    }
	 
	 @Override
	    public User getUser(int id) {
	        return repo.findById(id).orElseThrow();
	    }
	

}


