package com.example.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class TeamDTOTest {

	
	@InjectMocks
	static TeamDTO teamDTO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	@Test
	public void testDomain() {
		assertTrue(teamDTO instanceof TeamDTO);
	}
	
	@Test
	public void testSetGetID() {
		teamDTO.setId(1l);
	    Long result = teamDTO.getId();
	    Long id = 1l;
	    assertEquals(id, result);
	}
	
	@Test
	public void testSetGetName() {
		teamDTO.setName("Team Zhongli daddy");
	    String result = teamDTO.getName();
		String name = ("Team Zhongli daddy");	   
		assertEquals(name, result);
	}
	
	
	@Test
	public void testHashCode() {
		TeamDTO teamDto1 = new TeamDTO(1L, "Best bois");
		TeamDTO teamDto2 = new TeamDTO(1L, "Best bois");

		assertEquals(teamDto1.hashCode(), teamDto2.hashCode());
	}
	
	@Test
	public void testEquals() {
		TeamDTO teamDto1 = new TeamDTO(1L, "Best bois");
		TeamDTO teamDto2 = new TeamDTO(1L, "best bois");

		assertTrue(teamDto1.equals(teamDto2) && teamDto2.equals(teamDto1));
	}
	
	@Test 
	public void testHeroDTO() {
		HeroDTO heroDto1 = new HeroDTO();
		assertThat(heroDto1).isInstanceOf(HeroDTO.class);
	}
	
	@Test 
	public void testHeroDTO1() {
		HeroDTO heroDto1 = new HeroDTO("Hu Tao", "Pyro", "Polarm", 90l);
		assertThat(heroDto1).isInstanceOf(HeroDTO.class);
	}
	
}
