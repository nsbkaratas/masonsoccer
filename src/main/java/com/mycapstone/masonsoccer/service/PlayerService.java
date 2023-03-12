package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.PlayerRepoI;
import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Object saveOrUpdatePlayer(Player player) {
        if(playerRepoI.findByFirstNameAndLastName(player.getFirstName(),player.getLastName()).isPresent()){
            log.warn("player "+player.getFirstName()+" exist");
            String teamName= player.getTeam().getName();
            Player selectedPlayer=playerRepoI.findByFirstNameAndLastName(player.getFirstName(),player.getLastName()).get();
            Optional<Team> team = teamRepoI.findByName(teamName);
            selectedPlayer.setFirstName(player.getFirstName());
            selectedPlayer.setLastName(player.getLastName());
            selectedPlayer.setDateOfBirth(player.getDateOfBirth());
            teamService.addPlayer(teamName,player);
            return playerRepoI.save(player);
        }else{
            log.warn("player name"+ player.getFirstName()+" does not exist");
            return playerRepoI.save(player);
        }
    }
}
