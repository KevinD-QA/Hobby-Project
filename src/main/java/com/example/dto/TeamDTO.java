package com.example.dto;

import java.util.List;

public class TeamDTO {

	private Long id;

	private String name;

	private List<HeroDTO> heroes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HeroDTO> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<HeroDTO> heroes) {
		this.heroes = heroes;
	}

}