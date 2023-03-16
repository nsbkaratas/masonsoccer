package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.service.TeamService;
import com.mycapstone.masonsoccer.service.TrainingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


/**
 * @author nesibe karatas
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping(value={"", "/", "home"})
public class HomeController {

    CoachRepoI coachRepoI;
    TrainingService trainingService;

    TeamService teamService;
    @Autowired
    public HomeController(CoachRepoI coachRepoI, TrainingService trainingService, TeamService teamService) {
        this.coachRepoI = coachRepoI;
        this.trainingService = trainingService;
        this.teamService = teamService;
    }




    @GetMapping()
    public String home(Model model, HttpServletRequest request){
        log.debug("I am in home controller method");
        Coach coach= null;
        Principal p =request.getUserPrincipal();
        if(p != null){
            coach = coachRepoI.findByEmail(p.getName()).get();
        }
        model.addAttribute("coach", coach);
        model.addAttribute( "schedules", trainingService.findAll());

        return "home";
    }


    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
