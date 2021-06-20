package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.domain.Hero;
import com.example.dto.HeroDTO;
import com.example.repo.HeroRepo;

@Service
public class HeroService {

	private HeroRepo repo;
	
	private ModelMapper mapper;
	
	public HeroService(HeroRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public HeroDTO createHero(Hero hero) {
		Hero newHero = this.repo.save(hero);
		return this.mapper.map(newHero, HeroDTO.class);
	}
	
	public List<HeroDTO> getAllHeroes() {
//		List<Hero> characters = this.repo.findAll();
//		List<HeroDTO> dtos = new ArrayList<>();
//
//		HeroDTO dto = null;
//		for (Hero character : characters) {
//			dto = this.mapper.map(character, HeroDTO.class);
//			dtos.add(dto);
//		}
//
//		return dtos;
		return this.repo.findAll().stream().map(hero -> this.mapper.map(hero, HeroDTO.class)).collect(Collectors.toList());

	}
	
	public HeroDTO updateHeroes(Long id, Hero newHero) {
		Hero oldHero = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); //Fetches character that exists
		
		oldHero.setName(newHero.getName()); //Updates fields
		oldHero.setElement(newHero.getElement());
		oldHero.setWeapon(newHero.getWeapon());
		oldHero.setLevel(newHero.getLevel());
		oldHero.setTeam(newHero.getTeam());
		
		Hero updatedHero = this.repo.save(oldHero); //overwrites old data/records
		
		return this.mapper.map(updatedHero, HeroDTO.class); //Maps new data
	}
	
	public boolean deleteHero (Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
}
