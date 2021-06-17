package com.example.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Hero;
import com.example.domain.Team;
import com.example.dto.HeroDTO;
import com.example.dto.TeamDTO;


@Service
public class TeamMapper implements Mapper<Team, TeamDTO> {

	private HeroMapper heroMapper;

	public TeamMapper(HeroMapper heroMapper) {
		super();
		this.heroMapper = heroMapper;
	}

	@Override
	public TeamDTO mapToDTO(Team entity) {
		TeamDTO dto = new TeamDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		List<HeroDTO> heroes = new ArrayList<>();
		for (Hero hero : entity.getHeroes()) {
			heroes.add(this.heroMapper.mapToDTO(hero));
		}
		dto.setHeroes(heroes);
		return dto;
	}

	@Override
	public TeamDTO mapFromDTO(Team team) {
		// TODO Auto-generated method stub
		return null;
	}

}