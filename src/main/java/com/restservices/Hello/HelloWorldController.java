package com.restservices.Hello;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	private ResourceBundleMessageSource messageSource;

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
	
	@GetMapping("/hello-int")
	public String getMessageInI18Nformat(@RequestHeader (name = "Accept-Language", required=false) String locale) {
		return messageSource.getMessage("Label.hello", null, new Locale(locale));
	}

	public String getMessagesInI18NFormat() {
		return "Hello world I18N";
	}
}


