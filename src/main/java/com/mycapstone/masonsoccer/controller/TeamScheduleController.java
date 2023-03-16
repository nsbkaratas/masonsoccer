package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.data.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import com.mycapstone.masonsoccer.service.TeamService;
import com.mycapstone.masonsoccer.service.TrainingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

/**
 * @author nesibe karatas
 */

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping()
public class TeamScheduleController {

    @Autowired
    CoachRepoI coachRepoI;
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
    @GetMapping("/addtraining")
    public String addTrainingform(Model model, HttpServletRequest request){
        Coach coach= null;
        Principal p =request.getUserPrincipal();
        if(p != null){
            coach = coachRepoI.findByEmail(p.getName()).get();
        }
        model.addAttribute("coach", coach);
        model.addAttribute( "schedules", trainingService.findAll());
            return "addtraining";
}

//    @GetMapping()
//    public String viewSchedule(){
//        return "TeamSchedule";
//    }



}
