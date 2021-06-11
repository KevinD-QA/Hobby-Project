package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTO.CharacterDTO;
import Domain.Character;

import Service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
	
	private CharacterService service;
	
	@Autowired
	public CharacterController(CharacterService service) {
		this.service = service;
	}

	@PostMapping("/create") //Posting request to send data.
	public CharacterDTO createCharacter(@RequestBody Character character) {
		return this.service.createCharacter(character);
	}
	
	@PutMapping("/update/{id}") //Posting request to send data.
	public CharacterDTO updateCharacter(@RequestBody Character character, @PathVariable Long id) {
		return this.service.updateCharacter(id, character);
	}
	
	@DeleteMapping("/remove/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.delete(id);
	}
}
