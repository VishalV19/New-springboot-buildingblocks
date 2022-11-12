package com.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restservices.entities.Order;
import com.restservices.entities.User;
import com.restservices.exceptions.UserNotFoundException;
import com.restservices.repositories.UserRepository;
import com.restservices.services.UserServices;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private  UserServices userServices;
	

	
	//getUserById
		@GetMapping("/{id}")
		public Resource<User> getUserById(@PathVariable ("id") @Min(1) Long id){
			try {
				Optional<User> userOptional = userServices.getUserById(id);
				User user = userOptional.get();
				Long userid = user.getUserid();
				Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				Resource<User> finalResource = new Resource<User>(user);
				return finalResource;
			}catch(UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
					
		}
		
		//getAllUsers Method
		@GetMapping
		public Resources<User> getAllUsers() throws UserNotFoundException{
			List <User> allusers = userServices.getAllUsers();
		
			for (User user : allusers) {
				//self Link
				Long userid = user.getUserid();
				Link selflink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
				user.add(selflink);
				
				// Relationship link with getAllOrders
				Resources<Order> orders = (Resources<Order>) ControllerLinkBuilder.methodOn(OrderHateoasController.class)
						.getAllOrders(userid);
				Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
					user.add(ordersLink);
					
						
			}
			// Self link for getAllUsers
			Link selflinkgetAllUsers = ControllerLinkBuilder.linkTo(this.getClass()).withSelfRel();
			Resources<User> finalResources = new Resources<User>(allusers);
			return finalResources;
			
			
		}

}
