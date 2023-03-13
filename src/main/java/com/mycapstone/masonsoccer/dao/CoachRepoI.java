package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepoI extends JpaRepository<Coach, Integer> {
    Optional<Coach> findAllByFirstNameAndLastNameAllIgnoreCase(String firstName, String lastName);
}
