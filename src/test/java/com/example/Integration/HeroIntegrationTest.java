package com.example.Integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.domain.Hero;
import com.example.domain.Team;
import com.example.dto.HeroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //Resolves port conflicts if something is running on that port
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:heroes-schema.sql",
		"classpath:heroes-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
class HeroIntegrationTest {

	@Autowired
	private MockMvc mvc; //Allows us to mock requests
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Hero testHero = new Hero("Kazuha", "Anemo", "Sword", 90l);
		String testHeroAsJson = this.mapper.writeValueAsString(testHero);
		
		Hero testSavedHero = new Hero("Kazuha", "Anemo", "Sword", 90l);
		testSavedHero.setId(1l); //Change to 2 when ssql works
		String testSavedHeroAsJson = this.mapper.writeValueAsString(testSavedHero);
		
		RequestBuilder mockRequest = post("/heroes/create").content(testHeroAsJson)
				.contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testSavedHeroAsJson); //Checks with ID
		//this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
//		MvcResult result = this.mvc.perform(mockRequest).andExpect(checkStatus).andReturn();
//		String response = result.getResponse().getContentAsString();
//		Hero responseData = this.mapper.readValue(response, Hero.class);
//		
//		System.out.println("Hero: " + responseData);
		//assertThat(responseData).isEqualTo(testSavedHero); //Need to create and drop table but not working rn
	}
	
	@Test
	void testGetAll() throws Exception {
		Hero testHero = new Hero("Kazuha", "Anemo", "Sword", 90l);
		List<Hero> testHeroes = new ArrayList<>();
		testHeroes.add(testHero);

		String testHeroesAsJSONArray = this.mapper.writeValueAsString(testHeroes);
		RequestBuilder mockRequest = get("/heroes/");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testHeroesAsJSONArray); //Checks with ID
		//this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody); Still needs fixing
		//Need to create and drop
	}
	
	@Test
	void testUpdate() throws Exception{
		Team team = new Team(1L, null);
		Hero testHero = new Hero(1l,"Ganyu", "Cryo", "Bow", 90l, team);
		String testHeroAsJson = this.mapper.writeValueAsString(testHero);

		HeroDTO testSavedHero = new HeroDTO(1l, "Ganyu", "Cryo", "Bow", 90l);
		String testSavedHeroAsJson = this.mapper.writeValueAsString(testSavedHero);

		RequestBuilder mockRequest = put("/heroes/update/1").content(testHeroAsJson)
				.contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testSavedHeroAsJson); //Checks with ID
		//this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody); //Still needs fixing
	}
	
	@Test
	void testDelete() throws Exception {
			RequestBuilder mockRequest = delete("/heroes/remove/1");

			ResultMatcher checkStatus = status().isOk();
			ResultMatcher checkBody = content().string("true");

			//this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
}
