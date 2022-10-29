package com.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.restservices.entities.User;
import com.restservices.exceptions.UserExistsException;
import com.restservices.exceptions.UserNotFoundException;
import com.restservices.repositories.UserRepository;



//service
@Service
public class UserServices {

	// Autowire the UserRepository
	@Autowired 
	private UserRepository userRepository;
	
	// getAllUsers Method
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//CreateUser Method
	public User createUser (User user) throws UserExistsException{
		// if user exists using username
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		// if not exists throw UserExistsException
		if (existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		
		return userRepository.save(user);
	}
	
	//getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found is user repository");
		}
		
		return user;
	}

	//updateUserById
	
	public User updateUserById(Long id, User user)  throws UserNotFoundException{
Optional<User> optionaluser = userRepository.findById(id);
		
		if (!optionaluser.isPresent()) {
			throw new UserNotFoundException("User not found is user repository, provide the correct user id");
		}
		
		user.setId(id);
		return userRepository.save(user);
	} 
	
	//deleteUserById
	
	public  void deleteUserById(Long id) {
Optional<User> optionaluser = userRepository.findById(id);
		
		if (!optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  "User not found is user repository, provide the correct user id");
		}
		userRepository.deleteById(id);
		}
	
	
	// getUserbyUsername
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	
	
	}
	
	

