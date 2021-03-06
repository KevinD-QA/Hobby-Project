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

import com.example.dto.HeroDTO;
import com.example.service.HeroService;

@RestController
@RequestMapping("/heroes")
public class HeroController {
	
	@Autowired
	private HeroService service;
	
	public HeroController(HeroService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create") //Posting request to send data.
	public HeroDTO createHero(@RequestBody HeroDTO heroDTO) {
		return this.service.createHero(heroDTO);
	}
	
	@GetMapping("/")
	public List<HeroDTO> getHeroes() {
		return this.service.getAllHeroes();
	}
	
	@PutMapping("/update/{id}") //Posting request to send data.
	public HeroDTO updateHeroes(@RequestBody HeroDTO heroDTO, @PathVariable Long id) {
		return this.service.updateHeroes(id, heroDTO);
	}
	
	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.deleteHero(id);
	}
}
