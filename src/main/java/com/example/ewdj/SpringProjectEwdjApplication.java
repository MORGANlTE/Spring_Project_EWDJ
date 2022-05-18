package com.example.ewdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import service.JpaWedstrijdDao;
import service.VoetbalServiceImpl;
import service.WedstrijdDao;
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
	
	//bean maken van validation
	@Bean
	public BestellingValidation bestellingValidation()
	{
		return new BestellingValidation();
	}
	
	//talen instellen via dit
	@Bean MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("converter");
		return messageSource;
	}
	
	//onze wedstrijddao bean instellen
	@Bean
	public WedstrijdDao wedstrijdDao()
	{
		return new JpaWedstrijdDao();
	}
	
}
