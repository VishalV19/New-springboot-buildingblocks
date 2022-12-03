package com.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.validation.constraints.Min;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restservices.entities.User;
import com.restservices.exceptions.UserNotFoundException;
import com.restservices.services.UserServices;

import ch.qos.logback.core.filter.Filter;
import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping(value = "/jacksonfilter/users")

public class UserMappingJacksonController {

	// Autowired the UserService
	@Autowired
	private UserServices userServices;

	// getUserById
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {

			Optional<User> userOptional = userServices.getUserById(id);
			User user = userOptional.get();
			
			FilterProvider filterProvider = new SimpleFilterProvider();
				Set<String> fields = new HashSet<String>();
			//		fields.add("userid");
					fields.add("username");
					fields.add("ssn");
					
		//		.addFilter("userFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fields)); // Getting error in this line
			MappingJacksonValue mapper = new MappingJacksonValue(user);
		
			mapper.setFilters(filterProvider);
			return mapper;

		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}


	
	}


