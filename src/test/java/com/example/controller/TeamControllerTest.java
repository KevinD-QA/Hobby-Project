package com.example.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.example.controllers.TeamController;
import com.example.dto.TeamDTO;
import com.example.service.TeamService;



@SpringBootTest
@ActiveProfiles("test")
class TeamControllerTest {

	@Autowired
	private TeamController controller;
	
	@MockBean
	private TeamService service;
	
	@Test
	void testCreateTeam() {
		//Given
		//Team testTeam = new Team(1l,"Team Pyro");
		TeamDTO newDTO = new TeamDTO(1l, "Team Pyro");
		
		//When
		Mockito.when(this.service.createTeam(newDTO)).thenReturn(newDTO);
		
		//Then
		assertThat(this.controller.createTeam(newDTO)).isEqualTo(newDTO);
		Mockito.verify(this.service, Mockito.times(1)).createTeam(newDTO);
	}
	
	@Test
	void testGetTeam() {
		//Given
		TeamDTO dto = new TeamDTO(1l, "Geo scrubs");
		List<TeamDTO> getAll = List.of(dto);
		
		//When
		Mockito.when(this.service.getTeams()).thenReturn(getAll);
		
		//Then
		assertThat(this.controller.getTeams()).isEqualTo(getAll);
		Mockito.verify(this.service, Mockito.times(1)).getTeams();
	}
	
	@Test
	void testUpdateTeam() {
		//Given
		TeamDTO updateDTO = new TeamDTO("Zhongli Daddy");
		
		//When
		Long testID = 1l;
		TeamDTO updateDTO2 = new TeamDTO(1l, "Zhongli Daddy");
		Mockito.when(this.service.updateTeam(testID, updateDTO)).thenReturn(updateDTO2);
		
		//Then
		assertThat(this.service.updateTeam(testID, updateDTO)).isEqualTo(updateDTO2);
		Mockito.verify(this.service, Mockito.times(1)).updateTeam(testID, updateDTO);
	}
	
	@Test
	void testDeleteTeam() {
		//Given
		Long testID = 1l;
		
		//When
		Mockito.when(this.service.deleteTeam(testID)).thenReturn(true);
		
		//Then
		assertThat(this.controller.delete(testID)).isEqualTo(true);
		Mockito.verify(this.service, Mockito.times(1)).deleteTeam(testID);
	}

}
