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
import com.example.domain.Team;
import com.example.dto.TeamDTO;
import com.example.service.TeamService;

@SpringBootTest
@ActiveProfiles("test")
public class TeamControllerTest {

	@Autowired
	private TeamController controller;
	
	@MockBean
	private TeamService service;
	
	@Test
	void testCreateTeam() {
		//Given
		Team testTeam = new Team("Team Pyro");
		TeamDTO newDTO = new TeamDTO(1l, "Team Pyro");
		
		//When
		Mockito.when(this.service.createTeam(testTeam)).thenReturn(newDTO);
		
		//Then
		assertThat(this.controller.createTeam(testTeam)).isEqualTo(newDTO);
		Mockito.verify(this.service, Mockito.times(1)).createTeam(testTeam);
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
		Team updateTeam = new Team("Zhongli Daddy");
		TeamDTO updateDTO = new TeamDTO(1l, "Zhongli Daddy");
		
		//When
		Long testID = 1l;
		Mockito.when(this.service.updateTeam(testID, updateTeam)).thenReturn(updateDTO);
		
		//Then
		assertThat(this.service.updateTeam(testID, updateTeam)).isEqualTo(updateDTO);
		Mockito.verify(this.service, Mockito.times(1)).updateTeam(testID, updateTeam);
	}
	
	@Test
	void testDeleteTeam() {
		//Given
		Long testID = 1l;
		
		//When
		Mockito.when(this.service.delete(testID)).thenReturn(true);
		
		//Then
		assertThat(this.controller.delete(testID)).isEqualTo(true);
		Mockito.verify(this.service, Mockito.times(1)).delete(testID);
	}

}
