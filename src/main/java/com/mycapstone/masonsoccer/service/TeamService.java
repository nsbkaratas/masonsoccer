package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.models.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nesibe karatas
 */

@Service
@Slf4j
public class TeamService {

    @Autowired
    TeamRepoI teamRepoI;

    public List<Team> findAll() {

        return teamRepoI.findAll();
    }
    public void saveOrUpdate(Team team){
        log.info(team.toString());
        teamRepoI.save(team);
    }
}
