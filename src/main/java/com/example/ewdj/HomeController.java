package com.example.ewdj;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	//mapping voor redirecten naar /home
	@GetMapping("/")
	public String showHomePage() {
		return "redirect:/fifa";
	}
	
}
