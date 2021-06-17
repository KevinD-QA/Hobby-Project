package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Hero;


@Repository
public interface HeroRepo extends JpaRepository<Hero, Long> {
	
	List<Hero> findByElement(String Element);

}
