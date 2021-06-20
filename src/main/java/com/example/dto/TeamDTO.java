package com.example.dto;

import java.util.List;

public class TeamDTO {

	private Long id;

	private String name;

	private List<HeroDTO> heroes;
	

	public TeamDTO() {
		super();
	}


	public TeamDTO(Long id, String name, List<HeroDTO> heroes) {
		super();
		this.id = id;
		this.name = name;
		this.heroes = heroes;
	}
	

	public TeamDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public TeamDTO(String name) {
		super();
		this.name = name;
	}


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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heroes == null) ? 0 : heroes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamDTO other = (TeamDTO) obj;
		if (heroes == null) {
			if (other.heroes != null)
				return false;
		} else if (!heroes.equals(other.heroes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}