package com.mycapstone.masonsoccer.dao;

import com.mycapstone.masonsoccer.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepoI extends JpaRepository<Training, Integer> {

}
