package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.dao.TeamRepoI;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;

/**
 * @author nesibe karatas
 */

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/teams")
public class TeamController {
    TeamRepoI teamRepoI;

    TeamService teamService;
    Team team;
    Training training;
    TrainingService trainingService;
    TrainingRepoI trainingRepoI;
    @Autowired
    public TeamController(TeamService teamService, TrainingService trainingService, TrainingRepoI trainingRepoI,
                          TeamRepoI teamRepoI) {
        this.teamService = teamService;
        this.trainingService = trainingService;
        this.trainingRepoI = trainingRepoI;
        this.teamRepoI = teamRepoI;
    }

    @GetMapping()
    public String teamsList(Model model){
        log.debug("teams listed");
        model.addAttribute("teams", teamService.findAll());
        return "teams";
    }

    @GetMapping("/addteam")
    public String addTeamForm(Team team, Model model){
        log.debug("addTeamForm invoked");
        model.addAttribute("team", new Team());
        List<String> genderTypes=Arrays.asList("Female", "Male", "NA");
        model.addAttribute("genderTypes", genderTypes);
        List<String> listOfCoaches= Arrays.asList("José Mourinho","Alex Ferguson","Marcello Lippi","Arsène Wenger");
        model.addAttribute("listOfCoaches", listOfCoaches);
        return "addteam";
    }

    @PostMapping("/addteam/save")
    public String saveTeam(@ModelAttribute("team") Team team, Model model){

        model.addAttribute("team", teamService.saveOrUpdateTeam(team));
        return "redirect:/teams";
    }

    @GetMapping("/deleteTeam/{id}")
    public String deleteTeam(@PathVariable(name="id") Integer id) throws Exception{
        log.debug("Value of the id "+String.valueOf(id));
        teamService.deleteTeam(id);
        return "redirect:/teams";
    }
}