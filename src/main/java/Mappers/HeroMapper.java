package Mappers;

import org.springframework.stereotype.Service;

import DTO.HeroDTO;
import Domain.Heroes;

@Service
public class HeroMapper implements Mapper<Heroes, HeroDTO> {

	@Override
	public HeroDTO mapToDTO(Heroes character) {
		HeroDTO dto = new HeroDTO();
		
		dto.setId(character.getId());
		dto.setName(character.getName());
		dto.setWeapon(character.getWeapon());
		dto.setElement(character.getElement());
		dto.setLevel(character.getLevel());
		
		return dto;
	}

	@Override
	public HeroDTO mapFromDTO(Heroes dto) {
		// TODO Auto-generated method stub
		return null;
	}


}
