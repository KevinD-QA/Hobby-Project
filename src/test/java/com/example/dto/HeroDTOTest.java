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
class HeroDTOTest {

	
	@InjectMocks
	static HeroDTO herodto;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	@Test
	 void testDomain() {
		assertTrue(herodto instanceof HeroDTO);
	}
	
	@Test
	 void testSetGetID() {
		herodto.setId(1l);
	    Long result = herodto.getId();
	    Long id = 1l;
	    assertEquals(id, result);
	}
	
	@Test
	 void testSetGetName() {
		herodto.setName("Zhongli");
	    String result = herodto.getName();
		String name = ("Zhongli");	   
		assertEquals(name, result);
	}
	
	@Test
	 void testSetGetElement() {
		herodto.setElement("Geo");
	    String result = herodto.getElement();
		String element = ("Geo");	   
		assertEquals(element, result);
	}
	
	@Test
	 void testSetGetWeapon() {
		herodto.setWeapon("Polearm");
	    String result = herodto.getWeapon();
		String weapon = ("Polearm");	   
		assertEquals(weapon, result);
	}
	
	@Test
	 void testSetGetLevel() {
		herodto.setLevel(90l);
	    Long result = herodto.getLevel();
		Long level = (90l);	   
		assertEquals(level, result);
	}
	
	@Test
	 void testHashCode() {
		HeroDTO heroDto1 = new HeroDTO(1L, "Hu Tao", "Pyro", "Polarm", 90l);
		HeroDTO heroDto2 = new HeroDTO(1L, "Hu Tao", "Pyro", "Polarm", 90l);

		assertEquals(heroDto1.hashCode(), heroDto2.hashCode());
	}
	
	@Test
	 void testEquals() {
		HeroDTO heroDto1 = new HeroDTO(1L, "Hu Tao", "Pyro", "Polarm", 90l);
		HeroDTO heroDto2 = new HeroDTO(1L, "Hu Tao", "Pyro", "Polarm", 90l);

		assertTrue(heroDto1.equals(heroDto2) && heroDto2.equals(heroDto1));
	}
	
	@Test 
	 void testHeroDTO() {
		HeroDTO heroDto1 = new HeroDTO();
		assertThat(heroDto1).isInstanceOf(HeroDTO.class);
	}
	
	@Test 
	 void testHeroDTO1() {
		HeroDTO heroDto1 = new HeroDTO("Hu Tao", "Pyro", "Polarm", 90l);
		assertThat(heroDto1).isInstanceOf(HeroDTO.class);
	}
	
}
