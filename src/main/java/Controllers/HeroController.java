package Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import DTO.HeroDTO;
import Domain.Heroes;
import Service.HeroService;

@RestController
//@RequestMapping("/characters")
public class HeroController {
	
	private HeroService service;
	
	@Autowired
	public HeroController(HeroService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create") //Posting request to send data.
	public HeroDTO createCharacter(@RequestBody Heroes character) {
		return this.service.createCharacter(character);
	}
	
	@GetMapping("/")
	public List<HeroDTO> getCharacters() {
		return this.service.getAllCharacters();
	}
//	
//	@PutMapping("/update/{id}") //Posting request to send data.
//	public CharacterDTO updateCharacter(@RequestBody Character character, @PathVariable Long id) {
//		return this.service.updateCharacter(id, character);
//	}
//	
//	@DeleteMapping("/remove/{id}")
//	public boolean delete(@PathVariable Long id) {
//		return this.service.delete(id);
//	}
}
