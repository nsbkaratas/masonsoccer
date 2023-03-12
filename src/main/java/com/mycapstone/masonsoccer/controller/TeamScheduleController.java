package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import com.mycapstone.masonsoccer.service.TeamService;
import com.mycapstone.masonsoccer.service.TrainingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * @author nesibe karatas
 */

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping()
public class TeamScheduleController {

    TeamService teamService;
    Team team;
    Training training;
    TrainingService trainingService;
    TrainingRepoI trainingRepoI;
    @Autowired
    public TeamScheduleController(TeamService teamService, TrainingService trainingService, TrainingRepoI trainingRepoI) {
        this.teamService = teamService;
        this.trainingService = trainingService;
        this.trainingRepoI = trainingRepoI;
    }

    @GetMapping("/teamschedule")
    public String viewScheduleLink(@RequestParam("id") Integer id, Team team, Model model){
        log.warn("viewSchedule "+ id);
        model.addAttribute("team", teamService.findTeamById(id));
        model.addAttribute("schedules", teamService.findTeamTrainings(id));
        log.warn("this is --?"+  teamService.findTeamTrainings(id));
        return "TeamSchedule";
    }






//    @GetMapping()
//    public String viewSchedule(){
//        return "TeamSchedule";
//    }



}
