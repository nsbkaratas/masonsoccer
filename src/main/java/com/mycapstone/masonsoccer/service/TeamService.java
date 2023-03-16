package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.data.PlayerRepoI;
import com.mycapstone.masonsoccer.data.TeamRepoI;
import com.mycapstone.masonsoccer.data.TrainingRepoI;
import com.mycapstone.masonsoccer.exception.TeamNotFoundException;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import jakarta.transaction.Transactional;
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
@Transactional(rollbackOn = Exception.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamService {
    @Autowired
    private final PlayerRepoI playerRepoI;

    @Autowired
    CoachService coachService;
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
    return teamRepoI.findById(id).get();
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


    public Set<Training> findTeamTrainings(Integer id){
        log.warn("inside findTeamTrainings");
        Team team = findTeamById(id);
        log.warn("found team");
        return team.getTrainings();
    }

    public Set<Player> findTeamPlayers(Integer id) {
        log.warn("inside findteamplayers");
        Team team = findTeamById(id);
        log.warn("found team");
        return team.getPlayers();
    }

//    public void deleteTeam(Integer id) throws Exception{
//     Optional<Team> wantToDelete = teamRepoI.findById(id);
//     if(wantToDelete.isPresent())
//         teamRepoI.delete(wantToDelete.get());
//     else
//         throw new Exception("can't find team");
//    }
    public void deleteTeam(Team team){
        log.warn("delete team invoked in service layer");
        teamRepoI.delete(team);
    }


    public Team saveOrUpdateTeam(Team team, Coach coach){
        if(teamRepoI.findByNameAllIgnoreCase(team.getName()).isPresent()) {
            log.warn("team"+ team.getName()+" exist");
            String coachName= team.getCoach().getFirstName()+" "+team.getCoach().getLastName();
            Team selectedTeam = teamRepoI.findByNameAllIgnoreCase(team.getName()).get();
            selectedTeam.setName(team.getName());
            selectedTeam.setGender(team.getGender());
            selectedTeam.setAgeGroup(team.getAgeGroup());
            selectedTeam.setCoach(coach);
            return teamRepoI.save(selectedTeam);
        }else {
            log.debug("team name "+ team.getName()+" does not exist");
            return teamRepoI.save(team);
        }
    }


}


