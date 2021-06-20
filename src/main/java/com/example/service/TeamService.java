package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.domain.Team;
import com.example.dto.TeamDTO;
import com.example.repo.TeamRepo;

@Service
public class TeamService {

	private TeamRepo repo;

	private ModelMapper mapper;

	public TeamService(TeamRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public TeamDTO createTeam(TeamDTO teamDTO) {
		var teams = this.mapper.map(teamDTO, Team.class);
		Team saved = this.repo.save(teams);
		return this.mapper.map(saved, TeamDTO.class);
	}

	public List<TeamDTO> getTeams() {
		List<Team> Teams = this.repo.findAll();

		List<TeamDTO> dtos = new ArrayList<>();

		for (Team g : Teams) {
			TeamDTO dto = this.mapper.map(g, TeamDTO.class);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public TeamDTO updateTeam(Long id, Team newData) {
		Team existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); //Fetches team that exists
																									
		existing.setName(newData.getName()); //Updates fields

		Team updated = this.repo.save(existing); //overwrites old data/records

		return this.mapper.map(updated, TeamDTO.class); //Maps new data
	}

	public boolean deleteTeam(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
