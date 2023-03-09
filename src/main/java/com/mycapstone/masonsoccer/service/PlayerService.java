package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.PlayerRepoI;
import com.mycapstone.masonsoccer.models.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlayerService {
    @Autowired
    PlayerRepoI playerRepoI;

    public List<Player> showPlayers(){
        return playerRepoI.findAll();
    }
}
