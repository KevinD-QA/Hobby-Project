package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import DTO.HeroDTO;
import Domain.Heroes;
import Mappers.HeroMapper;
import Repo.HeroRepo;

@Service
public class HeroService {

	private HeroRepo repo;
	
	private HeroMapper mapper;
	
	public HeroService(HeroRepo repo, HeroMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	public HeroDTO createCharacter(Heroes character) {
		Heroes newCharacter = this.repo.save(character);
		return this.mapper.mapToDTO(newCharacter);
	}
	
	public HeroDTO readCharacter(Long id) {
		Optional<Heroes> optionalCharacter = this.repo.findById(id);
		Heroes selected = optionalCharacter.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(selected);
	} 
	
	public List<HeroDTO> getAllCharacters() {
		List<Heroes> characters = this.repo.findAll();
		List<HeroDTO> dtos = new ArrayList<>();

		HeroDTO dto = null;
		for (Heroes character : characters) {
			dto = this.mapper.mapToDTO(character);
			dtos.add(dto);
		}

		return dtos;
	}
	
//	public List<Character> findByElement(String element) {
//		return this.repo.findByElement(element);
//	} //CHANGE TO READ
//	
	public HeroDTO updateCharacter(Long id, Heroes character) {
		Heroes update = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException()); //Fetches character that exists
		
		update.setName(character.getName()); //Updates fields
		update.setElement(character.getElement());
		update.setWeapon(character.getWeapon());
		update.setLevel(character.getLevel());
		
		Heroes updated = this.repo.save(update); //overwriting old record
		
		return this.mapper.mapToDTO(updated); //Maps new data
	}
	
	public boolean delete (Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
}
