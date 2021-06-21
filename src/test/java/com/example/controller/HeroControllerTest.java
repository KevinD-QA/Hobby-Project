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
import com.example.dto.HeroDTO;
import com.example.service.HeroService;

@SpringBootTest
@ActiveProfiles("test")
class HeroControllerTest {
	
	@Autowired
	private HeroController controller;
	
	@MockBean
	private HeroService service;
	
	
	@Test
	void testCreateHero() {
		//Given 
		HeroDTO newDTO = new HeroDTO(1l, "Hu Tao", "Pyro", "Polearm" , 90l);
		
		//When
		HeroDTO newDTO2 = new HeroDTO(1l, "Hu Tao", "Pyro", "Polearm" , 90l);
		Mockito.when(this.service.createHero(newDTO)).thenReturn(newDTO2);
		
		//Then
		assertThat(this.controller.createHero(newDTO)).isEqualTo(newDTO2);
		Mockito.verify(this.service, Mockito.times(1)).createHero(newDTO2);
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
		HeroDTO updateDTO = new HeroDTO("Xiao", "Anemo", "Polearm", 90l);
		
		//When
		Long testID = 1l;
		HeroDTO updateDTO2 = new HeroDTO(1l, "Xiao", "Anemo", "Polearm", 90l);
		Mockito.when(this.service.updateHeroes(testID,updateDTO)).thenReturn(updateDTO2);
		
		//Then
		assertThat(this.controller.updateHeroes(updateDTO, testID)).isEqualTo(updateDTO2);
		Mockito.verify(this.service, Mockito.times(1)).updateHeroes(testID, updateDTO);		
	}
	
	@Test
	void testDeleteHero() {
		//Given
		Long testID = 1l;
		
		//When
		Mockito.when(this.service.deleteHero(testID)).thenReturn(true);
		
		//Then
		assertThat(this.controller.delete(testID)).isEqualTo(true);
		Mockito.verify(this.service, Mockito.times(1)).deleteHero(testID);		
	}
	

}
