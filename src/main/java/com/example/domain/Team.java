package com.example.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "team")
	@JsonIgnore
	private List<Hero> heroes;

	
	public Team(Long id, String name, List<Hero> heroes) {
		super();
		this.id = id;
		this.name = name;
		this.heroes = heroes;
	}
	

	public Team(Long id) {
		super();
		this.id = id;
	}


	public Team(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Team(String name) {
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

	public List<Hero> getHeroes() {
		return heroes;
	}

	public void setHeroes(List<Hero> heroes) {
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
		Team other = (Team) obj;
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