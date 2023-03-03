package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepoI extends JpaRepository<Player, Integer> {
}
