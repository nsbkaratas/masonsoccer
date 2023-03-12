package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepoI extends JpaRepository<Training, Integer> {

    Optional<Training> findByDateAndDurationAndFieldNameAndStartTimeAllIgnoreCase
            (String date, String duration, String fieldName, String startTime);
}
