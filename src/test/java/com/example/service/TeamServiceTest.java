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

import com.example.domain.Team;
import com.example.dto.TeamDTO;
import com.example.repo.TeamRepo;

@SpringBootTest
@ActiveProfiles("test")
class TeamServiceTest {

	@Autowired
	private TeamService service;

	@MockBean
	private TeamRepo repo;
	
	@MockBean
	private ModelMapper mapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testTeamService() {
		TeamService tService = new TeamService(repo, mapper);
		assertThat(tService).isInstanceOf(TeamService.class);
	}
	
	@Test
	void testCreateTeam() {
		//Given
		Team newTeam = new Team("Pyro Squad");
		Team newTeam2 = new Team(1l,"Pyro Squad");
		TeamDTO teamDTO = new TeamDTO(1l,"Pyro Squad");
		
		//When
		Mockito.when(this.mapper.map(teamDTO, Team.class)).thenReturn(newTeam);
		Mockito.when(this.repo.save(newTeam)).thenReturn(newTeam2);
		Mockito.when(this.mapper.map(newTeam2, TeamDTO.class)).thenReturn(teamDTO);
		//Then
		assertThat(this.service.createTeam(teamDTO)).isEqualTo(teamDTO);
		Mockito.verify(this.repo, Mockito.times(1)).save(newTeam);
	}
	
	@Test
	void testReadTeam() {
		//Given
		List<Team> teams = new ArrayList<>();
		Team team1 = new Team(1l,"Team One bang");
		Team team2 = new Team(2l, "Team two bang");
		teams.add(team1);
		teams.add(team2);
		
		List<TeamDTO> teamDTO = new ArrayList<>();
		TeamDTO teamDTO1 = new TeamDTO(1l, "Team One bang");
		TeamDTO teamDTO2 = new TeamDTO(1l, "Team two bang");
		teamDTO.add(teamDTO1);
		teamDTO.add(teamDTO2);
		
		//When
		Mockito.when(this.repo.findAll()).thenReturn(teams);
		Mockito.when(this.mapper.map(teamDTO1, TeamDTO.class)).thenReturn(teamDTO1);
		Mockito.when(this.mapper.map(teamDTO2, TeamDTO.class)).thenReturn(teamDTO2);		
		
		//Then
//		Mockito.verify(this.mapper, Mockito.times(1)).map(team1, TeamDTO.class);
//		Mockito.verify(this.mapper, Mockito.times(1)).map(team2, TeamDTO.class);
	}
	@Test
	void testUpdate() {
		//Given
		Team testData = new Team("Zhongli Carry");
		Team team = new Team(1L, "Hu tao carry");
		Team updatedTeam = new Team(1l, "Zhongli Carry");
		TeamDTO updatedDTO = new TeamDTO(1L, "Hu tao carry");

		//When
		Long testID = 1l;
		Mockito.when(this.repo.findById(testID)).thenReturn(Optional.of(team));
		Mockito.when(this.repo.save(updatedTeam)).thenReturn(updatedTeam);

		//Then
//		assertThat(this.service.updateTeam(testID, testData)).isEqualTo(updatedDTO);
//
//		Mockito.verify(this.repo, Mockito.times(1)).findById(testID);
//		Mockito.verify(this.repo, Mockito.times(1)).save(updatedTeam);
	}
		
		@Test
		void testDeleteTask() {
			//Given
			Long teamID = 1L;

			//When
			Mockito.when(this.repo.existsById(teamID)).thenReturn(false);

			//Then
			assertThat(this.service.deleteTeam(teamID)).isTrue();
			Mockito.verify(this.repo, Mockito.times(1)).existsById(teamID);
		}
}
