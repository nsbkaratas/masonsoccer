package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.exception.TeamNotFoundException;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoachService {
    private final CoachRepoI coachRepoI;

    public CoachService(CoachRepoI coachRepoI) {
        this.coachRepoI = coachRepoI;
    }

    //find all coaches
    public List<Coach> findAll() {
       return coachRepoI.findAll();
    }


    public void removeTeam(Integer id, Team team){
        log.warn("removePlayer from team in service layer invoked");
        Coach coach = coachRepoI.findById(id).orElseThrow();
        coach.removeTeam(team);
        coachRepoI.save(coach);

    }
    public Optional<Coach> findByFirstName(String coachName) {
        return coachRepoI.findByFirstName(coachName);
    }

    public Coach findById(Integer coachId) {
        return coachRepoI.findById(coachId).orElseThrow();
    }


//    public Team findCoachById(Integer id){
//        log.warn("findTeambyID method invoked");
//        return coachRepoI.findById(id);
//    }


}
