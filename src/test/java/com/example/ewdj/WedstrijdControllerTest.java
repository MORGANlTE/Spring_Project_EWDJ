package com.example.ewdj;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import domain.Wedstrijd;
import service.VoetbalServiceImpl;
import service.WedstrijdDao;
import validator.BestellingValidation;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class WedstrijdControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	private WedstrijdController wedstrijdController; 
	
	@Mock
	private WedstrijdDao wedstrijdDaoMock;
	
	@Mock
	private VoetbalServiceImpl voetbalServiceImpl;
	
	@BeforeAll
	public void before()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		MockitoAnnotations.openMocks(this);
		wedstrijdController = new WedstrijdController();
		mockMvc = MockMvcBuilders.standaloneSetup(wedstrijdController).build();
	}
	
	//REST TESTEN:
	@Test
	public void testRestPagina() throws Exception{
		//injecteren
		ReflectionTestUtils.setField(wedstrijdController, "wedstrijdDao", wedstrijdDaoMock);
		//vb wedstrijd
		Wedstrijd w = new Wedstrijd(1L, new String[] { "België", "Canada" }, 26, 21, "Al Bayt Stadium", 35);
		//mock trainen
		Mockito.when(wedstrijdDaoMock.getWedstrijdById(Mockito.anyInt())).thenReturn(w);
		//vb returnwaardes
		String data[] = new String[] {"België","Canada"};
		
		mockMvc.perform(get("/rest/fifaDetail/1"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(Arrays.asList(data)))
			;
	}
	

	

}
