package com.example.ewdj;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import domain.Bestelling;
import domain.Wedstrijd;
import service.VoetbalServiceImpl;
import service.WedstrijdDao;
import validator.BestellingValidation;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class HomePaginaControllerTest {
	
	private HomePaginaController controller;
	private MockMvc mockMvc;
	
	//mocks maken vd verschillende klassen die we gebruiken
	@Mock
	private BestellingValidation validation;
	
	@Mock
	private WedstrijdDao wedstrijdDaoMock;
	
	@Mock
	private VoetbalServiceImpl voetbalServiceImpl;

	
	@BeforeAll
	public void before()
	{
		MockitoAnnotations.openMocks(this);
		controller = new HomePaginaController();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	//HOMEPAGINA TESTEN:
	//testen of de homepagina opgevraagd kan worden met bestaande stadiums
	@Test
	public void testHomePagina() throws Exception{
		ReflectionTestUtils.setField(controller, "wedstrijdDao", wedstrijdDaoMock);
		
		List<String> eadh = new ArrayList<>();
		eadh.add("Al Bayt Stadium");
		
		Mockito.when(wedstrijdDaoMock.getStadiums()).thenReturn(eadh);
		
		mockMvc.perform(get("/fifa"))
			.andExpect(status().isOk())
			.andExpect(view().name("homePage"))
			.andExpect(model().attributeExists("stadiums"))
			.andExpect(model().attribute("stadiums", Arrays.asList("Al Bayt Stadium")));
	}
	
	//als de wedstrijd uitverkocht was testen
	@Test
	public void testHomePaginaUitverkocht() throws Exception{
		mockMvc.perform(get("/fifa").param("uitverkocht", "1"))
			.andExpect(status().isOk())
			.andExpect(view().name("homePage"))
			.andExpect(model().attributeExists("stadiums"))
			.andExpect(model().attributeExists("errorBestelling"))
			.andExpect(model().attribute("errorBestelling","De voetbalmatch is uitverkocht!"));
	}
	
	//als de tickets gekocht zijn testen
	@Test
	public void testHomePaginaTicketGekocht() throws Exception{
		
		mockMvc.perform(get("/fifa").param("verkocht", "12"))
			.andExpect(status().isOk())
			.andExpect(view().name("homePage"))
			.andExpect(model().attributeExists("stadiums"))
			.andExpect(model().attributeExists("verkochteTickets"))
			.andExpect(model().attribute("verkochteTickets","12 tickets verkocht"));
	}
	
	//LIJSTWEDSTRIJDEN TESTEN:
	//als je de lijst opvraagt:
	@Test
	public void testLijstWedstrijden() throws Exception
	{
		mockMvc.perform(post("/fifa").param("stadium", "Al Bayt Stadium"))
		.andExpect(status().isOk())
		.andExpect(view().name("lijstWedstrijden"));
	}
	
	//TICKET AANKOPEN:
	@Test
	public void testTicketKopen() throws Exception {
		ReflectionTestUtils.setField(controller, "wedstrijdDao", wedstrijdDaoMock);
		ReflectionTestUtils.setField(controller, "bestellingValidation", validation);
		
		Wedstrijd w = new Wedstrijd(1L, new String[] { "Belgi??", "Canada" }, 26, 21, "Al Bayt Stadium", 35);
		Mockito.when(wedstrijdDaoMock.getWedstrijdById(1)).thenReturn(w);


		mockMvc.perform(post("/fifa/1").flashAttr("bestelling", new Bestelling("test@gmail.com", "1", "10", "20")))
		.andExpect(redirectedUrl("/fifa?verkocht=1"))
		.andExpect(view().name("redirect:/fifa?verkocht=1"))
		;
		
	}
	
	@Test
	public void testTicketKopenFaalt() throws Exception {
		Wedstrijd w = new Wedstrijd(1L, new String[] { "Belgi????", "Canada" }, 26, 21, "Al Bayt Stadium", 0);

		Mockito.when(wedstrijdDaoMock.getWedstrijdById(1)).thenReturn(w);

		ReflectionTestUtils.setField(controller, "wedstrijdDao", wedstrijdDaoMock);
		ReflectionTestUtils.setField(controller, "bestellingValidation", validation);

		mockMvc.perform(post("/fifa/1").flashAttr("bestelling", new Bestelling("test", "2", "80", "20")))
		.andExpect(model().hasErrors());
		;
		
	}
	
	//rollen even testen (op homepagina):
	@Test
	@WithMockUser(roles = "USER")
	public void testHomePaginaRolUser() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ReflectionTestUtils.setField(controller, "wedstrijdDao", wedstrijdDaoMock);
		ArrayList<String> rollen = new ArrayList<>();
		for (GrantedAuthority a : auth.getAuthorities()) {
			rollen.add(a.getAuthority());
		}
		mockMvc.perform(get("/fifa"))
			.andExpect(model().attribute("naam", auth.getName()))
			.andExpect(model().attribute("rollen", rollen))
			;
	}
	
	//rollen even testen (op homepagina):
	@Test
	@WithMockUser(roles = "ADMIN")
	public void testHomePaginaRolAdmin() throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ArrayList<String> rollen = new ArrayList<>();
		for (GrantedAuthority a : auth.getAuthorities()) {
			rollen.add(a.getAuthority());
		}
		mockMvc.perform(get("/fifa"))
			.andExpect(model().attribute("naam", auth.getName()))
			.andExpect(model().attribute("rollen", rollen))
			;
	}
	
	
	
	

}
