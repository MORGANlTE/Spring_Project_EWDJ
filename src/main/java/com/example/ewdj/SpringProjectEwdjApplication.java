package com.example.ewdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
}
