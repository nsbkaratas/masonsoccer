package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.data.PlayerRepoI;
import com.mycapstone.masonsoccer.data.TeamRepoI;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerService {

    @Autowired
    TeamRepoI teamRepoI;
    @Autowired
    TeamService teamService;

    @Autowired
    PlayerRepoI playerRepoI;

    public List<Player> showPlayers(){
        return playerRepoI.findAll();
    }

    public Optional<Player> findById(Integer id)
    {
        return playerRepoI.findById(id);

    }
    public void deletePlayer(Player player) {
        log.warn("delete player invoked in service layer");
       playerRepoI.delete(player);
    }

    public Player saveOrUpdatePlayer(Player player, Team team) {

        if(playerRepoI.findByFirstNameAndLastName(player.getFirstName(),player.getLastName()).isPresent()){
            log.warn("player "+player.getFirstName()+" exist");
            String teamName= player.getTeam().getName();
            Player selectedPlayer=playerRepoI.findByFirstNameAndLastName(player.getFirstName(),player.getLastName()).get();
            selectedPlayer.setFirstName(player.getFirstName());
            selectedPlayer.setLastName(player.getLastName());
            LocalDate dob = player.getDateOfBirth();
            selectedPlayer.setDateOfBirth(dob);
            LocalDate now = LocalDate.now();
            Period period = Period.between(dob, now);
            int age = period.getYears();
//            if(age==3||age==5 && player.getGender()=="NA"){
//                selectedPlayer.setTeam();
//            }

            return playerRepoI.save(player);
        }else{
            log.warn("player name "+ player.getFirstName()+" does not exist");
            player.setTeam(team);
            return playerRepoI.save(player);
        }
    }
}
