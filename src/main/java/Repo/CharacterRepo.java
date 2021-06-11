package Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Domain.Character;


@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {
	
	List<Character> findByNmame(String name);

}
