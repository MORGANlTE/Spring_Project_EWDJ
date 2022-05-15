package com.example.ewdj;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.assertj.core.util.Arrays;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class WedstrijdControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;

	
	@BeforeAll
	public void before()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	//REST TESTEN:
	@Test
	public void testRestPagina() throws Exception{
		String data[] = new String[] {"België","Canada"};
		mockMvc.perform(get("/rest/fifaDetail/1"))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$").value(Arrays.asList(data)))
			;
	}
	

	

}
