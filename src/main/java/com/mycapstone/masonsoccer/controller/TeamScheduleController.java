package com.mycapstone.masonsoccer.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

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

    @Autowired
    public TeamScheduleController(TeamService teamService, TrainingService trainingService) {
        this.teamService = teamService;
        this.trainingService = trainingService;
    }



    @GetMapping("/teamschedule")
    public String viewScheduleLink(@RequestParam("id") Integer id, Model model){
        log.warn("viewSchedule "+ id);
        model.addAttribute("schedules", teamService.findTeamTrainings(id));
        log.warn("this is --?"+  teamService.findTeamTrainings(id));
        return "TeamSchedule";
    }

    @GetMapping("/training/delete/{id}")
    public String deleteTraining(@PathVariable("id") Integer id) throws Exception{
        Training training = trainingService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor Id:" + id));
        trainingService.delete(training);
//        return "TeamSchedule";
        return "redirect:/teamschedule";
    }


//    @GetMapping()
//    public String viewSchedule(){
//        return "TeamSchedule";
//    }



}
