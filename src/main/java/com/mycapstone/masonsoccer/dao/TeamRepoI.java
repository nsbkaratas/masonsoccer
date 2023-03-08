package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepoI extends JpaRepository<Team, Integer> {
    Optional<Team> findByName(String name);
}
