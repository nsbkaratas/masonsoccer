package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepoI extends JpaRepository<Player, Integer> {
}
