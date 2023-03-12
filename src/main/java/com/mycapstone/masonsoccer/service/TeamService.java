package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.PlayerRepoI;
import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.exception.TeamNotFoundException;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamService {
    @Autowired
    private final PlayerRepoI playerRepoI;

    TrainingRepoI trainingRepoI;
    TeamRepoI teamRepoI;
    @Autowired
    public TeamService(TrainingRepoI trainingRepoI, TeamRepoI teamRepoI,
                       PlayerRepoI playerRepoI) {
        this.trainingRepoI = trainingRepoI;
        this.teamRepoI = teamRepoI;
        this.playerRepoI = playerRepoI;
    }

    public List<Team> findAll() {
        log.warn("findAll team invoked");
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
    public Optional<Team> findByName(String name){
        log.warn("findbyname invoked");
        return teamRepoI.findByName(name);
    }

    public void removePlayer(Integer id, Player player){
        log.warn("removePlayer from team in service layer invoked");
        Team team = teamRepoI.findById(id).orElseThrow();
        team.removePlayer(player);
        teamRepoI.save(team);

    }

    public void addPlayer(String name, Player player){
        log.warn("addPlayer to team in service layer invoked");
//        Team team = teamRepoI.findByName(name).orElseThrow();
//        player =playerRepoI.save(player);
//        team.addPlayer(player);
//        teamRepoI.save(team);
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

    public void deleteTeam(Integer id) throws Exception{
     Optional<Team> wantToDelete = teamRepoI.findById(id);
     if(wantToDelete.isPresent())
         teamRepoI.delete(wantToDelete.get());
     else
         throw new Exception("can't find team");
    }

    public Team saveOrUpdateTeam(Team team){
        if(teamRepoI.findByNameAllIgnoreCase(team.getName()).isPresent()) {
            log.debug("team " + team.getName() + " exist");
            Team currentTeam = teamRepoI.findByNameAllIgnoreCase(team.getName()).get();
            currentTeam.setName(team.getName());
            currentTeam.setAgeGroup(team.getAgeGroup());
            currentTeam.setGender(team.getGender());
            return teamRepoI.save(currentTeam);
        }else {
            log.debug("team name "+ team.getName()+" does not exist");
            return teamRepoI.save(team);
        }
    }

}


