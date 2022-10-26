package com.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

// Simple Method
// URI - 
//helloworld	
	
//	@RequestMapping(method = RequestMethod.GET,  path ="/helloworld" )
	
	@GetMapping("/helloworld01")
	public String hellowWorld() {
		return "Welcome to Hello World01";
	}
	
	@GetMapping("/helloworld-bean01")
	public UserDetails helloWorldBean() {
		return new UserDetails("Vishal", "Singh","Lucknow");
	}

}


