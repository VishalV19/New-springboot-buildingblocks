package com.restservices.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.hibernate.cache.spi.support.NaturalIdNonStrictReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.restservices.entities.User;
import com.restservices.exceptions.UserExistsException;
import com.restservices.exceptions.UserNameNotFoundException;
import com.restservices.exceptions.UserNotFoundException;
import com.restservices.services.UserServices;

//Controller

@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {
	
	// Autowired the UserService
	@Autowired
	private UserServices userServices;
	
	//getAllUsers Method
	@GetMapping
	public List<User> getAllUsers(){
		return userServices.getAllUsers();
	}
	
	//Create user Method
	//@RequestBody Annotation
	//@PostMapping Annotation
	
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user,  UriComponentsBuilder builder) {
		try {
				userServices.createUser(user);
			HttpHeaders headers = new HttpHeaders();
				headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
				return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
				
		} catch(UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  ex.getMessage());
		}
	}
	
	//getUserById
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable ("id") @Min(1) Long id){
		try {
			return userServices.getUserById(id);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
				
	}
	
	//updateUserById
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		
		try {
			return userServices.updateUserById(id, user);
		} catch(UserNotFoundException  ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  ex.getMessage());
		}
		
		
	}
	
	//deleteUserById
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userServices.deleteUserById(id);
	}
	
	// getUserbyUsername
	@GetMapping("/byusername/{username}")
	public User getUsername(@PathVariable ("username") String username) throws UserNameNotFoundException {
		User user =  userServices.getUserByUsername(username);
		if(user == null)
			throw new UserNameNotFoundException("Username: '" + username + "' not found in User repository");
		return user;
	}
	

	
	
}
