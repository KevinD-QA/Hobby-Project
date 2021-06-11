package Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import DTO.CharacterDTO;
import Mappers.CharacterMapper;
import Repo.CharacterRepo;
import Domain.Character;

@Service
public class CharacterService {

	private CharacterRepo repo;
	
	private CharacterMapper mapper;
	
	public CharacterService(CharacterRepo repo, CharacterMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public CharacterDTO createCharacter(Character character) {
		Character newCharacter = this.repo.save(character);
		return this.mapper.mapToDTO(newCharacter);
	}
	
	public CharacterDTO findCharacter(Long id) {
		Optional<Character> optionalCharacter = this.repo.findById(id);
		Character selected = optionalCharacter.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(selected);
	}
	
	public List<Character> findByElement(String element) {
		return this.repo.findByElement(element);
	}
	
	public CharacterDTO updateCharacter(Long id, Character character) {
		Character update = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); //Fetches character that exists
		
		update.setName(character.getName()); //Updates fields
		update.setElement(character.getElement());
		update.setWeapon(character.getWeapon());
		update.setLevel(character.getLevel());
		
		Character updated = this.repo.save(update); //overwriting old record
		
		return this.mapper.mapToDTO(updated); //Maps new data
	}
	
	public boolean delete (Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
