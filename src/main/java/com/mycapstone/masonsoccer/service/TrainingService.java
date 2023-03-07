package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nesibe karatas
 */
@Service
public class TrainingService {

    @Autowired
    TrainingRepoI trainingRepoI;
    public List<Training> findAllTraining() {
        return trainingRepoI.findAll();
    }
}
