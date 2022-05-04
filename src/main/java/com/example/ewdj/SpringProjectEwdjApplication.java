package com.example.ewdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import service.VoetbalServiceImpl;
import validator.BestellingValidation;

@SpringBootApplication
public class SpringProjectEwdjApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectEwdjApplication.class, args);
	}

	@Bean
	public VoetbalServiceImpl voetbalServiceImpl()
	{
		return new VoetbalServiceImpl();
	}
	
	@Bean
	public BestellingValidation bestellingValidation()
	{
		return new BestellingValidation();
	}
	
	@Bean MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("converter");
		return messageSource;
	}
}
