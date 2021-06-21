package com.example.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.domain.Hero;


@SpringBootTest
@ActiveProfiles("test")
class TeamDTOTest {

	
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
	public void testSetGetHeroes() {
		teamDTO.setHeroes(null);
	    List<HeroDTO> result = teamDTO.getHeroes();
	    List<HeroDTO> heroes = (null);	   
		assertEquals(heroes, result);
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
		TeamDTO teamDto2 = new TeamDTO(1L, "Best bois");
		
		assertTrue(teamDto1.equals(teamDto2) && teamDto2.equals(teamDto1));
	}
	
	@Test 
	public void testTeamDTO() {
		TeamDTO teamDTO = new TeamDTO();
		assertThat(teamDTO).isInstanceOf(TeamDTO.class);
	}
	
	@Test 
	public void testTeamDTO1() {
		TeamDTO teamDTO = new TeamDTO("Team big dmg");
		assertThat(teamDTO).isInstanceOf(TeamDTO.class);
	}
	
}
