package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.domain.Hero;
import com.example.dto.HeroDTO;
import com.example.repo.HeroRepo;

@SpringBootTest
@ActiveProfiles("test")
class HeroServiceTest {
	
	@Autowired
	private HeroService service;

	@MockBean
	private HeroRepo repo;
	
	@MockBean
	private ModelMapper mapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	 void testHeroService() {
		HeroService hService = new HeroService(repo, mapper);
		assertThat(hService).isInstanceOf(HeroService.class);
	}
	
	@Test
	 void testCreateHero() {
		//Given
		Hero newHero = new Hero("Ganyu", "Cryo", "Bow", 90l);
		Hero newHero2 = new Hero(1l, "Ganyu", "Cryo", "Bow", 90l);
		HeroDTO heroDTO = new HeroDTO(1l, "Ganyu", "Cryo", "Bow", 90l);
		
		//When
		Mockito.when(this.mapper.map(heroDTO, Hero.class)).thenReturn(newHero);
		Mockito.when(this.repo.save(newHero)).thenReturn(newHero2);
		Mockito.when(this.mapper.map(newHero2, HeroDTO.class)).thenReturn(heroDTO);
		//Then
		assertThat(this.service.createHero(heroDTO)).isEqualTo(heroDTO);
		Mockito.verify(this.repo, Mockito.times(1)).save(newHero);
	}
	
	@Test
	 void testReadHero() {
		//Given
		List<Hero> heroes = new ArrayList<>();
		Hero hero1 = new Hero(1l, "Eula", "Cryo", "Claymore", 80l);
		Hero hero2 = new Hero(2l, "Ningguang", "Geo", "Book", 85l);
		heroes.add(hero1);
		heroes.add(hero2);
		
		List<HeroDTO> heroesDTO = new ArrayList<>();
		HeroDTO heroDTO1 = new HeroDTO(1l, "Eula", "Cryo", "Claymore", 80l);
		HeroDTO heroDTO2 = new HeroDTO(2l, "Ningguang", "Geo", "Book", 85l);
		heroesDTO.add(heroDTO1);
		heroesDTO.add(heroDTO2);
		
		//When
		Mockito.when(this.repo.findAll()).thenReturn(heroes);
		Mockito.when(this.mapper.map(heroDTO1, HeroDTO.class)).thenReturn(heroDTO1);
		Mockito.when(this.mapper.map(heroDTO2, HeroDTO.class)).thenReturn(heroDTO2);		
		
		//Then
		//assertThat(this.service.readHero(1l)).isEqualTo(heroesDTO);
		//Mockito.verify(this.repository, Mockito.times(1)).findByElement("Cryo");
//		Mockito.verify(this.mapper, Mockito.times(1)).map(hero1, HeroDTO.class);
//		Mockito.verify(this.mapper, Mockito.times(1)).map(hero2, HeroDTO.class);
	}
	@Test
	void testUpdate() {
		//Given
		Hero testData = new Hero("Eula", "Cryo", "Claymore", 80l);
		Hero hero = new Hero(1L, "Ningguang", "Geo", "Book", 85l);
		Hero updatedHero = new Hero(1l, "Eula", "Cryo", "Claymore", 80l);
		HeroDTO updatedDTO = new HeroDTO(1L, "Eula", "Cryo", "Claymore", 80l);

		//When
		Long testID = 1l;
		Mockito.when(this.repo.findById(testID)).thenReturn(Optional.of(hero));
		Mockito.when(this.repo.save(updatedHero)).thenReturn(updatedHero);

		//Then
//		assertThat(this.service.updateHeroes(testID, testData)).isEqualTo(updatedDTO);
//
//		Mockito.verify(this.repo, Mockito.times(1)).findById(testID);
//		Mockito.verify(this.repo, Mockito.times(1)).save(updatedHero);
	}
		
		@Test
		 void testDeleteTask() {
			//Given
			Long heroID = 1L;

			//When
			Mockito.when(this.repo.existsById(heroID)).thenReturn(false);

			//Then
			assertThat(this.service.deleteHero(heroID)).isTrue();
			Mockito.verify(this.repo, Mockito.times(1)).existsById(heroID);
		}

}
