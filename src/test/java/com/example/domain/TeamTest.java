package com.example.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TeamTest {

	@InjectMocks
	static Team team;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	 void testDomain() {
		assertTrue(team instanceof Team);
	}
	
	@Test
	 void testSetGetID() {
		team.setId(1l);
	    Long result = team.getId();
	    Long id = 1l;
	    assertEquals(id, result);
	}
	
	@Test
	 void testSetGetName() {
		team.setName("Team BangBang");
	    String result = team.getName();
	    String name = "Team BangBang";
	    assertEquals(name, result);
	}
	
	
	@Test
	 void testTeam() {
		Team team = new Team("Team Geo");
		assertTrue(team instanceof Team);	
	}
	
	@Test
	 void testTeam2() {
		Team team = new Team(1l, "Team Geo");
		assertTrue(team instanceof Team);	
	}
	
	@Test
	 void testHashCode() {
		
		Team  team1 = new Team(1L, "Geo Brothers");
		Team team2 = new Team(1L, "Geo Brothers");

		assertEquals(team1.hashCode(), team2.hashCode());
	}
	
	@Test
	void testEquals() {
		Team  team1 = new Team(1L, "Geo Brothers");
		Team team2 = new Team(1L, "Geo Brothers");

		assertTrue(team1.equals(team2) && team2.equals(team1));
	}
}
