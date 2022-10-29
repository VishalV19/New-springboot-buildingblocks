package com.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.entities.User;
import com.restservices.services.UserServices;

//Controller

@RestController
public class UserController {
	
	// Autowired the UserService
	@Autowired
	private UserServices userServices;
	
	//getAllUsers Method
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userServices.getAllUsers();
	}
	
	//Create user Method
	//@RequestBody Annotation
	//@PostMapping Annotation
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userServices.createUser(user);
	}
	
	//getUserById
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable ("id") Long id){
		return userServices.getUserById(id);
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return userServices.updateUserById(id, user);
	}
	
	//deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userServices.deleteUserById(id);
	}
	
	// getUserbyUsername
	@GetMapping("/users/byusername/{username}")
	public User getUsername(@PathVariable ("username") String username) {
		return userServices.getUserByUsername(username);
	}
	
	
}
