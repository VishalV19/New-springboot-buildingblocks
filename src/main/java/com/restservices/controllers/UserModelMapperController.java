package com.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservices.dto.UserMmDto;
import com.restservices.entities.User;
import com.restservices.exceptions.UserNotFoundException;
import com.restservices.services.UserServices;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//getUserById
		@GetMapping("/{id}")
		public UserMmDto getUserById(@PathVariable ("id") @Min(1) Long id) throws UserNotFoundException{
			
			Optional<User> userOptional = userServices.getUserById(id);
					
			if (!userOptional.isPresent()) {
				throw new UserNotFoundException("user not found");
			}
				User user = userOptional.get();
				
				
				UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
				return userMmDto;
				
			
		}
		

		
		
		
		
		
		
		
		
		
}
