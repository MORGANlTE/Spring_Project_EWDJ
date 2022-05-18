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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
		Mockito.when(wedstrijdDaoMock.getStadiumByWedstrijdId("1")).thenReturn()
		String data[] = new String[] {"België","Canada"};
		mockMvc.perform(get("/rest/fifaDetail/1"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(Arrays.asList(data)))
			;
	}
	

	

}
