package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.controllers.HeroController;
import com.example.domain.Hero;
import com.example.dto.HeroDTO;
import com.example.service.HeroService;

@SpringBootTest
@ActiveProfiles("test")
public class HeroControllerTest {
	
	@Autowired
	private HeroController controller;
	
	@MockBean
	private HeroService service;
	
	
	@Test
	void testCreateHero() {
		//Given 
		Hero testHero = new Hero("Hu Tao", "Pyro", "Polearm" , 90l);
		HeroDTO newDTO = new HeroDTO(1l, "Hu Tao", "Pyro", "Polearm" , 90l);
		
		//When
		Mockito.when(this.service.createHero(testHero)).thenReturn(newDTO);
		
		//Then
		assertThat(this.controller.createHero(testHero)).isEqualTo(newDTO);
		Mockito.verify(this.service, Mockito.times(1)).createHero(testHero);
	}
	
	@Test
	void testGetHero() {
		//Given
		HeroDTO dto = new HeroDTO(2l, "Zhongli", "Geo", "Polearm", 90l);
		List<HeroDTO> getAll = List.of(dto);
		
		//When
		Mockito.when(this.service.getAllHeroes()).thenReturn(getAll);
		
		//Then
		assertThat(this.controller.getHeroes()).isEqualTo(getAll);
		Mockito.verify(this.service, Mockito.times(1)).getAllHeroes();		
	}
	
	@Test
	void testUpdateHero() {
		//Given
		Hero updateHero = new Hero("Xiao", "Anemo", "Polearm", 90l);
		HeroDTO updateDTO = new HeroDTO(1l, "Xiao", "Anemo", "Polearm", 90l);
		
		//When
		Long testID = 1l;
		Mockito.when(this.service.updateHeroes(testID,updateHero)).thenReturn(updateDTO);
		
		//Then
		assertThat(this.controller.updateHeroes(updateHero, testID)).isEqualTo(updateDTO);
		Mockito.verify(this.service, Mockito.times(1)).updateHeroes(testID, updateHero);		
	}
	
	@Test
	void testDeleteHero() {
		//Given
		Long testID = 1l;
		
		//When
		Mockito.when(this.service.delete(testID)).thenReturn(true);
		
		//Then
		assertThat(this.controller.delete(testID)).isEqualTo(true);
		Mockito.verify(this.service, Mockito.times(1)).delete(testID);		
	}
	

}
