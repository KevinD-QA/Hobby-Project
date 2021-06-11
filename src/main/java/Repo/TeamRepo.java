package Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Domain.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Long> {

}
