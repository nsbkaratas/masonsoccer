package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.models.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author nesibe karatas
 */

public class TeamService {

    @Autowired
    TeamRepoI teamRepoI;
    public List<Team> findAllTeam() {
        return teamRepoI.findAll();
    }
}
