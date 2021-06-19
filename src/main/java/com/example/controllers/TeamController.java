package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Team;
import com.example.dto.TeamDTO;
import com.example.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {

	private TeamService service;

	@Autowired
	public TeamController(TeamService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public TeamDTO createTeam(@RequestBody Team team) {
		return this.service.createTeam(team);
	}

	@GetMapping("/find/{id}")
	public TeamDTO find(@PathVariable Long id) {
		return this.service.findTeam(id);
	}

	@GetMapping("/")
	public List<TeamDTO> getTeams() {
		return this.service.getTeams();
	}

	@PutMapping("/update/{id}")
	public TeamDTO updateTeam(@RequestBody Team team, @PathVariable Long id) {
		return this.service.updateTeam(id, team);
	}

	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}

}
