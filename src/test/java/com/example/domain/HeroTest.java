package com.example.domain;

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
class HeroTest {

	@InjectMocks
	static Hero hero;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	@Test
	 void testDomain() {
		assertTrue(hero instanceof Hero);
	}
	
	@Test
	 void testSetGetID() {
		hero.setId(1l);
	    Long result = hero.getId();
	    Long id = 1l;
	    assertEquals(id, result);
	}
	
	@Test
	 void testSetGetName() {
		hero.setName("Zhongli");
	    String result = hero.getName();
		String name = ("Zhongli");	   
		assertEquals(name, result);
	}
	
	@Test
	 void testSetGetElement() {
		hero.setElement("Geo");
	    String result = hero.getElement();
		String element = ("Geo");	   
		assertEquals(element, result);
	}
	
	@Test
	 void testSetGetWeapon() {
		hero.setWeapon("Polearm");
	    String result = hero.getWeapon();
		String weapon = ("Polearm");	   
		assertEquals(weapon, result);
	}
	
	@Test
	 void testSetGetLevel() {
		hero.setLevel(90l);
	    Long result = hero.getLevel();
		Long level = (90l);	   
		assertEquals(level, result);
	}
	
	@Test
	 void testSetGetTeam() {
		hero.setTeam(null);
	    Team result = hero.getTeam();
	    Team team = null;
	    assertEquals(team, result);
	}
	
	@Test
	 void testHero() {
		Hero hero = new Hero();
		assertThat(hero).isInstanceOf(Hero.class);
	}
	
	@Test
	 void testHashCode() {
		Hero  hero1 = new Hero(1L, "Hu Tao", "Pyro", "Polarm", 90l);
		Hero hero2 = new Hero(1L, "Hu Tao", "Pyro", "Polarm", 90l);

		assertEquals(hero1.hashCode(), hero2.hashCode());
	}
	
	@Test
	 void testEquals() {
		Hero hero1 = new Hero(1L, "Hu Tao", "Pyro", "Polearm", 90l);
		Hero hero2 = new Hero(1L, "Hu Tao", "Pyro", "Polearm", 90l);

		assertTrue(hero1.equals(hero2) && hero2.equals(hero1));
	}
}
	