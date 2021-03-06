package com.example.ewdj;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import domain.Wedstrijd;
import service.WedstrijdDao;

//restlaag
@RestController
@RequestMapping(value="/rest")
public class WedstrijdController {
	
    @Autowired
    private WedstrijdDao wedstrijdDao;
    
	@GetMapping(value="/fifaDetail/{id}")
	public List<String> getWedstrijd(@PathVariable("id") int wedstrijd)
	{
		//zoals vb project enkel 2 waarden teruggeven
		Wedstrijd w = wedstrijdDao.getWedstrijdById(wedstrijd);
		
		//landen instellen
		List<String> landen = new ArrayList<>();
		landen.add(w.getLanden()[0]);
		landen.add(w.getLanden()[1]);
		
		return landen;
	}
	
}
