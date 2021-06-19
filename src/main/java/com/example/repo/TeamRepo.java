package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {

}
