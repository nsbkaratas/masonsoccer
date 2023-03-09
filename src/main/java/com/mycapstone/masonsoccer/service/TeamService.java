package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.exception.TeamNotFoundException;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author nesibe karatas
 */

@Service
@Slf4j
public class TeamService {
    @Autowired
    private TrainingRepoI trainingRepoI;

    @Autowired
    TeamRepoI teamRepoI;

    public List<Team> findAll() {

        return teamRepoI.findAll();
    }
    public void saveOrUpdate(Team team){
        log.info(team.toString());
        teamRepoI.save(team);
    }

//    public List<Training> findSchedule(Integer id) {
//        return trainingRepoI.findAllById(id);
//    }
    public Team findTeamById(Integer id){
        log.warn("findTeambyID method invoked");
    return teamRepoI.findById(id).orElseThrow(()->new TeamNotFoundException(id));
}

//    public Set<Training> findTeamTrainings(Integer id){
//        log.warn("find team training method");
//        log.warn("Number of trainings"+ findTeamById(id).getTrainings());
//        return findTeamById(id).getTrainings();
//    }
public Set<Training> findTeamTrainings(Integer id){
    log.warn("inside findTeamTrainings");
    Team team = findTeamById(id);
    log.warn("found team");
    return team.getTrainings();
}
}


