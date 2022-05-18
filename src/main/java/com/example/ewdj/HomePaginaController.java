package com.example.ewdj;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import domain.Bestelling;
import domain.Wedstrijd;
import service.VoetbalServiceImpl;
import service.WedstrijdDao;
import validator.BestellingValidation;

//Controller zorgt ervoor dat het gedetecteerd wordt door Spring, requestmapping
//zorgt ervoor dat dit de homepagina opvraagt
@Controller
@RequestMapping("/fifa")
public class HomePaginaController{
	
	@Autowired
	private VoetbalServiceImpl voetbalServiceImpl;
	
	
	@Autowired
	private BestellingValidation bestellingValidation;
	
    @Autowired
    private WedstrijdDao wedstrijdDao;
    
    @GetMapping
    public String showHomePage(Model model, @RequestParam(required = false) String verkocht, @RequestParam(required = false) String uitverkocht) {
//    	model.addAttribute("wedstrijdDao", wedstrijdDao.findAll());
//        
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"België", "Canada"}, 26, 21, "Al Bayt Stadium", 35));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Brazilië", "Zwitserland"}, 27, 18, "Al Bayt Stadium", 21));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Marroko", "Kroatië"}, 28, 15, "Al Bayt Stadium", 5));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Spanje", "Duitsland"}, 28, 18, "Al Bayt Stadium", 95));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Frankrijk", "Denemarken"}, 30, 15, "Al Thumama Stadium", 45));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Argentinië", "Mexico"}, 30, 18, "Al Bayt Stadium", 10));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Engeland", "USA"}, 31, 18, "Al Bayt Stadium", 22));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Nederland", "Qatar"}, 31, 21, "Al Thumama Stadium",  16));
//        wedstrijdDao.insert(new Wedstrijd(new String[]{"Oostenrijk", "Frankrijk"}, 20, 16, "Ghelamco Arena", 12));


    	List<String> stadiums = wedstrijdDao.getStadiums();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	ArrayList<String> rollen = new ArrayList<>();
    	if (auth != null)
    	{
    		for (GrantedAuthority a : auth.getAuthorities()) {
    			rollen.add(a.getAuthority());
    		}
    		
    		String naam = auth.getName();
    		model.addAttribute("naam", naam);
    		model.addAttribute("rollen", rollen);
    		
    	}
    	
    	model.addAttribute("stadiums", stadiums);
    	if(verkocht != null)
    		model.addAttribute("verkochteTickets", String.format("%s ticket%s verkocht", verkocht, (Integer.parseInt(verkocht)==1)?"":"s"));
    	if(uitverkocht != null)
    		model.addAttribute("errorBestelling", "De voetbalmatch is uitverkocht!");
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

    @PostMapping
    public String onSubmit(@RequestParam("stadium") String stadium, Model model)
    {
    	model.addAttribute("stadium", stadium);
    	//List<WedstrijdTicket> tickets = voetbalServiceImpl.getWedstrijdenByStadium(stadium);
    	
    	
    	model.addAttribute("wedstrijden", wedstrijdDao.getWedstrijdByStadium(stadium));
    	return "lijstWedstrijden";
    }
    
    @GetMapping("/{id}")
    public String showTickets(@PathVariable(value="id") String id, Model model) {
    	
    	model.addAttribute("id", id);
    	
    	Wedstrijd wedstrijd = wedstrijdDao.get(Long.parseLong(id));
    	String stadium = wedstrijdDao.getStadiumByWedstrijdId(id);
    	if (wedstrijd.uitverkocht())
    	{
        	List<String> stadiums = wedstrijdDao.getStadiums();
	    	model.addAttribute("stadiums", stadiums);
    		return "redirect:/fifa?uitverkocht";
    	}
    	model.addAttribute("stadium", stadium);
    	model.addAttribute("wedstrijd", wedstrijd);
    	model.addAttribute("id", id);
    	model.addAttribute("bestelling", new Bestelling());
    	System.out.println(wedstrijd.getTickets());

        return "matchTickets";
    }

	
	@PostMapping("/{id}")
	public String onSubmitTickets(@Valid Bestelling bestelling, BindingResult result, @PathVariable(value="id") String id, Model model, Locale locale)
	{
		bestellingValidation.validate(bestelling, result);

		if (result.hasErrors())
        {
	    	
	    	model.addAttribute("id", id);
	    	Wedstrijd w = wedstrijdDao.getWedstrijdById(Integer.parseInt(id));
	    	model.addAttribute("stadium", w.getStadium());
	    	model.addAttribute("wedstrijd", w);
	    	model.addAttribute("id", id);
	    	model.addAttribute("bestelling", bestelling);
	    	
			return "matchTickets";
        }
		else {
	    	List<String> stadiums =  wedstrijdDao.getStadiums();
	    	int aantalTickets = Integer.parseInt(bestelling.getTicketAantal());
	    	
	    	model.addAttribute("stadiums", stadiums);
	    	
	    	Wedstrijd dezeWedstrijd = wedstrijdDao.getWedstrijdById(Integer.parseInt(id));
	    	
	    	System.out.println("wedstrijdtickets: "+ dezeWedstrijd.getTickets());
	    	
	    	int gekochtAantal = dezeWedstrijd.ticketsKopen(aantalTickets);
	    	System.out.println("wedstrijdtickets: "+ dezeWedstrijd.getTickets());
	    	wedstrijdDao.update(dezeWedstrijd);
			return "redirect:/fifa?verkocht="+gekochtAantal;
		}
    }
	
	
}

