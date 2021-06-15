package Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Domain.Heroes;


@Repository
public interface HeroRepo extends JpaRepository<Heroes, Long> {
	
	List<Heroes> findByElement(String Element);

}
