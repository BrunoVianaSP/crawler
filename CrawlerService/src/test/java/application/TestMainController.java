package application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@ContextConfiguration(classes = Application.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TestMainController {

	private static final String BASE_URL = "http://localhost";

	@LocalServerPort 
	int port;
	
	@Autowired
	protected MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEndpoint() throws Exception {
		String user = "admin";
		String pass = "admin";
	
		mockMvc.perform(get(BASE_URL + port + "/feed/download/{user}/{pass}", user, pass)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
//	@Test
//	public void testEndpoint() throws Exception {
//		User u = new User();
//		u.setUsername("admin");
//		u.setPass("admin");
//	
//		mockMvc.perform(post("/feed/download/")
//				.content(new Gson().toJson(u))
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andDo(print());
//	}


}
