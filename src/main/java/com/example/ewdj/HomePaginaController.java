package com.example.ewdj;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import utility.Message;
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
    public String showHomePage(Model model, @RequestParam(required = false) String verkocht, @RequestParam(required = false) String uitverkocht) {
    	List<String> stadiums = voetbalServiceImpl.getStadiumList();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	ArrayList<String> rollen = new ArrayList<>();
    	for (GrantedAuthority a : auth.getAuthorities()) {
    		rollen.add(a.getAuthority());
		}
		
    	String naam = auth.getName();
    	
    	model.addAttribute("stadiums", stadiums);
    	if(verkocht != null)
    		model.addAttribute("verkochteTickets", String.format("%s ticket%s verkocht", verkocht, (Integer.parseInt(verkocht)==1)?"":"s"));
    	if(uitverkocht != null)
    		model.addAttribute("errorBestelling", "De voetbalmatch is uitverkocht!");
    	model.addAttribute("naam", naam);
    	model.addAttribute("rollen", rollen);
        return "homePage";
    }
    
    @GetMapping(value = "/login")
    public String login(Model model, Principal principal, @RequestParam(value= "logout", required= false) String logout) 
    {
    	model.addAttribute("username", principal.getName());
    	if(logout!= null) {
    		model.addAttribute("msg", "U bent succesvol afgemeld.");
    	}
    	return "login";    
    }
    
    @GetMapping("/{id}")
    public String showTickets(@PathVariable(value="id") String id, Model model) {
    	
    	model.addAttribute("id", id);
    	
    	WedstrijdTicket ticket = voetbalServiceImpl.getWedstrijd(id);
    	tempTicket = ticket;
    	tempId = id;
    	String stadium = voetbalServiceImpl.getStadium(id);
    	if (ticket.uitverkocht())
    	{
        	List<String> stadiums = voetbalServiceImpl.getStadiumList();
	    	model.addAttribute("stadiums", stadiums);
    		return "redirect:/fifa?uitverkocht";
    	}
    	model.addAttribute("stadium", stadium);
    	model.addAttribute("ticket", ticket);
    	model.addAttribute("id", id);
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
	public String onSubmitTickets(@Valid Bestelling bestelling, BindingResult result, @PathVariable(value="id") String id, Model model, Locale locale)
	{
		bestellingValidation.validate(bestelling, result);

		if (result.hasErrors())
        {
	    	
	    	model.addAttribute("id", id);
	    	WedstrijdTicket ticket = voetbalServiceImpl.getWedstrijd(id);
	    	tempTicket = ticket;
	    	tempId = id;
	    	String stadium = voetbalServiceImpl.getStadium(id);
	    	model.addAttribute("stadium", stadium);
	    	model.addAttribute("ticket", ticket);
	    	model.addAttribute("id", id);
			
			return "matchTickets";
        }
		else {
	    	List<String> stadiums = voetbalServiceImpl.getStadiumList();
	    	int aantalTickets = Integer.parseInt(bestelling.getTicketAantal());
	    	model.addAttribute("stadiums", stadiums);
	    	int gekochtAantal = voetbalServiceImpl.getWedstrijd(id).ticketsKopen(aantalTickets);
			return "redirect:/fifa?verkocht="+gekochtAantal;
		}
    }
	
	
}

