package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.User;
import com.springboot.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userservice;

    
    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        User savedUser = userservice.saveUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

   
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userservice.getAllUser();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable int id){
        User user = userservice.getUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }
}
