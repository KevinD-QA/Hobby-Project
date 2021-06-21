package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
	private Long id;
	
	
	private String name;
	private String weapon;
	private String element;
	private Long level;
	
	@ManyToOne
	private Team team;
	
	public Hero() {
		super();
	}
	
	public Hero(String name, String weapon, String element, Long level) {
		super();
		this.name = name;
		this.weapon = weapon;
		this.element = element;
		this.level = level;
	}
	
	public Hero(Long id, String name, String weapon, String element, Long level) {
		super();
		this.id = id;
		this.name = name;
		this.weapon = weapon;
		this.element = element;
		this.level = level;
	}
	
	public Hero(Long id, String name, String weapon, String element, Long level, Team team) {
		super();
		this.id = id;
		this.name = name;
		this.weapon = weapon;
		this.element = element;
		this.level = level;
		this.team = team;
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
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", weapon=" + weapon + ", element=" + element + ", level="
				+ level + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((weapon == null) ? 0 : weapon.hashCode());
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
		Hero other = (Hero) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}
	
}
