package Mappers;

import org.springframework.stereotype.Service;

import DTO.CharacterDTO;
import Domain.Character;

@Service
public class CharacterMapper implements Mapper<Character, CharacterDTO> {

	@Override
	public CharacterDTO mapToDTO(Character character) {
		CharacterDTO dto = new CharacterDTO();
		
		dto.setId(character.getId());
		dto.setName(character.getName());
		dto.setWeapon(character.getWeapon());
		dto.setElement(character.getElement());
		dto.setLevel(character.getLevel());
		
		return dto;
	}

	@Override
	public CharacterDTO mapFromDTO(Character dto) {
		// TODO Auto-generated method stub
		return null;
	}


}
