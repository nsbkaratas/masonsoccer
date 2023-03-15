package com.mycapstone.masonsoccer.data;

import com.mycapstone.masonsoccer.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepoI extends JpaRepository<Player, Integer> {
    Optional<Player> findByFirstNameAndLastName(String fName, String lName);
}
