package com.example.ewdj;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Bestelling;
import validator.BestellingValidation;

@Controller
@RequestMapping("/bestelling")
public class BestellingController {

	@Autowired
	BestellingValidation bestellingValidation;
        
   
}
