package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author nesibe karatas
 */
@Service
@Slf4j
public class TrainingService {


    TrainingRepoI trainingRepoI;
    TeamRepoI teamRepoI;

    @Autowired
    public TrainingService(TrainingRepoI trainingRepoI, TeamRepoI teamRepoI) {
        this.trainingRepoI = trainingRepoI;
        this.teamRepoI = teamRepoI;
    }
    public List<Training> findAllTraining() {
        return trainingRepoI.findAll();
    }


    public void saveOrUpdate(Training training){
        log.info(training.toString());
        trainingRepoI.save(training);
    }

    public void addTeam(Team team, Training training) throws Exception {
        if(teamRepoI.findById(team.getId()).isPresent()) {
            team.addTraining(training);
            teamRepoI.save(team);

            log.warn("team added to training");
        }else{
            throw new Exception("Adding team to training Failed");
        }
    }
    public void delete(Training training) {
        trainingRepoI.delete(training);
    }



    public Optional<Training> findById(Integer id) {
        return trainingRepoI.findById(id);
    }
}
