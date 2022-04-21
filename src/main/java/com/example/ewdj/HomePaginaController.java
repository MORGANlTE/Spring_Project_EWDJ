package com.example.ewdj;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Bestelling;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.VoetbalServiceImpl;
import validator.BestellingValidation;

//Controller zorgt ervoor dat het gedetecteerd wordt door Spring, requestmapping
//zorgt ervoor dat dit de homepagina opvraagt
@Controller
@RequestMapping("/fifa")
public class HomePaginaController{
	
	@Autowired
	private VoetbalServiceImpl voetbalServiceImpl;

	private WedstrijdTicket tempTicket;
	private String tempId;
	
	@Autowired
	private BestellingValidation bestellingValidation;
       
    @GetMapping
    public String showHomePage(Model model) {
    	List<String> stadiums = voetbalServiceImpl.getStadiumList();
    	model.addAttribute("stadiums", stadiums);
        return "homePage";
    }
    
    @GetMapping("/{id}")
    public String showTickets(@PathVariable(value="id") String id, Model model) {
    	
    	model.addAttribute("id", id);
    	
    	WedstrijdTicket ticket = voetbalServiceImpl.getWedstrijd(id);
    	tempTicket = ticket;
    	tempId = id;
    	String stadium = voetbalServiceImpl.getStadium(id);
    	model.addAttribute("stadium", stadium);
    	model.addAttribute("ticket", ticket);
    	model.addAttribute("bestelling", new Bestelling());

        return "matchTickets";
    }

	@PostMapping
	public String onSubmit(@RequestParam("stadium") String stadium, Model model)
	{
		model.addAttribute("stadium", stadium);
		List<WedstrijdTicket> tickets = voetbalServiceImpl.getWedstrijdenByStadium(stadium);

		model.addAttribute("tickets", tickets);
		return "lijstWedstrijden";
	}
	
	@PostMapping("/{id}")
	public String onSubmitTickets(@Valid Bestelling bestelling, BindingResult result, Model model)
	{
		bestellingValidation.validate(bestelling, result);

		if (result.hasErrors())
        {
	    	String stadium = voetbalServiceImpl.getStadium(tempId);
	    	model.addAttribute("stadium", stadium);
	    	model.addAttribute("ticket", tempTicket);

			return "matchTickets";
        }
		else {
	    	List<String> stadiums = voetbalServiceImpl.getStadiumList();
	    	model.addAttribute("stadiums", stadiums);
			return "homePage";
		}
    }
	
	
}

