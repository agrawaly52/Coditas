package com.git;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.git.controller.GitController;
import com.git.service.impl.GitServiceImpl;


/*
 * @Auther Yash Agrawal
 * Test class   to test the  GitController api's
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = { GitController.class, GitServiceImpl.class })
public class GitApplicationTests {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	private static final int isOk = 200;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/*
	 * Method  use to test the rest api to fetch all the repositories.
	 * It is using the MockMvc object to test  the api
	 */
	@Test
	public void testGitRepositoryResponseCode() throws Exception {

		mockMvc.perform(get("/git/repos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/*
	 * Method  testing the rest api using httpClient dependancy.
	 * AssertEquals to perform if the status code is ok or not
	 */
	@Test
	public void testGithubResponse() {
		// Given
		HttpUriRequest request = new HttpGet("http://localhost:8081/git/projects?owner=vglo&repos=SalonWebService");

		// When
		HttpResponse httpResponse;
		try {
			httpResponse = HttpClientBuilder.create().build().execute(request);

			// Then
			assertEquals(httpResponse.getStatusLine().getStatusCode(), isOk);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
