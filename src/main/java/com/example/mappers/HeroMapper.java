package com.example.mappers;

import org.springframework.stereotype.Service;

import com.example.domain.Hero;
import com.example.dto.HeroDTO;

@Service
public class HeroMapper implements Mapper<Hero, HeroDTO> {

	@Override
	public HeroDTO mapToDTO(Hero hero) {
		HeroDTO dto = new HeroDTO();
		
		dto.setId(hero.getId());
		dto.setName(hero.getName());
		dto.setWeapon(hero.getWeapon());
		dto.setElement(hero.getElement());
		dto.setLevel(hero.getLevel());
		
		return dto;
	}

	@Override
	public HeroDTO mapFromDTO(Hero dto) {
		// TODO Auto-generated method stub
		return null;
	}


}
