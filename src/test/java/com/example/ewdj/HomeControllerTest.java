package com.example.ewdj;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class HomeControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@BeforeAll
	public void before()
	{
		//gewone setup, geen mocks trainen etc/geen klassen injecten ofzo
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	//test of je geredirect wordt
	@Test
	public void testHomePaginaShowing() throws Exception{
		mockMvc.perform(get("/"))
			.andExpect(redirectedUrl("/fifa"));
	}
}
