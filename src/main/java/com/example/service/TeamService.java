package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.domain.Team;
import com.example.dto.TeamDTO;
import com.example.mappers.TeamMapper;
import com.example.repo.TeamRepo;

@Service
public class TeamService {

	private TeamRepo repo;

	private TeamMapper mapper;

	public TeamService(TeamRepo repo, TeamMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public TeamDTO createTeam(Team team) {
		Team saved = this.repo.save(team);
		return this.mapper.mapToDTO(saved);
	}

	public TeamDTO findTeam(Long id) {
		Optional<Team> optionalTeam = this.repo.findById(id);
		Team found = optionalTeam.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}

	public TeamDTO updateTeam(Long id, Team newData) {
		Team existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); //Fetches team that exists
																									

		existing.setName(newData.getName()); //Updates fields

		Team updated = this.repo.save(existing); //overwrites old data/records

		return this.mapper.mapToDTO(updated); //Maps new data
	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public List<TeamDTO> getTeams() {
		List<Team> Teams = this.repo.findAll();

		List<TeamDTO> dtos = new ArrayList<>();

		for (Team g : Teams) {
			TeamDTO dto = this.mapper.mapToDTO(g);
			dtos.add(dto);
		}
		return dtos;
	}

}
