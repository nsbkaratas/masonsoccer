package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepoI extends JpaRepository<Team, Integer> {
}
