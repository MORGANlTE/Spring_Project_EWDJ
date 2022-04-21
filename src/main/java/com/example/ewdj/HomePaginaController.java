package com.example.ewdj;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.VoetbalServiceImpl;

//Controller zorgt ervoor dat het gedetecteerd wordt door Spring, requestmapping
//zorgt ervoor dat dit de homepagina opvraagt
@Controller
@RequestMapping("/fifa")
public class HomePaginaController{
	@Autowired
	private VoetbalServiceImpl voetbalServiceImpl;
       
    @GetMapping
    public String showHomePage(Model model) {
    	List<String> stadiums = voetbalServiceImpl.getStadiumList();
    	model.addAttribute("stadiums", stadiums);
        return "homePage";
    }
        
	@PostMapping
	public String onSubmit(@RequestParam("stadium") String stadium, Model model)
	{
		model.addAttribute("stadium", stadium);
		List<WedstrijdTicket> tickets = voetbalServiceImpl.getWedstrijdenByStadium(stadium);

		model.addAttribute("tickets", tickets);
		return "lijstWedstrijden";
	}
	
}

