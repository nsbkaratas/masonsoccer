package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.dao.CoachRepoI;
import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.service.TeamService;
import com.mycapstone.masonsoccer.service.TrainingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author nesibe karatas
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(value={"", "/", "home"})
public class HomeController {


    TrainingService trainingService;

    TeamService teamService;

    @Autowired
    public HomeController(TrainingService trainingService, TeamService teamService) {
        this.trainingService = trainingService;
        this.teamService = teamService;
    }

    @GetMapping()
    public String home(Model model){
        log.debug("I am in home controller method");
        model.addAttribute( "teams", teamService.findAll());

        return "home";
    }

}
