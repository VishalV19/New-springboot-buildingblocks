package com.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;







//
@SpringBootApplication
public class NewSpringbootbuildingblocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewSpringbootbuildingblocksApplication.class, args);
	}
	
	
	
//	@Bean
//	public LocaleResolver localeResolver() {
//		org.springframework.web.servlet.LocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
//		((AbstractResourceBasedMessageSource) localeResolver).setDefaultLocale(Locale.US);
//		return (org.hibernate.validator.spi.messageinterpolation.LocaleResolver) localeResolver;
//	}
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
				
		
	} 
	

}
