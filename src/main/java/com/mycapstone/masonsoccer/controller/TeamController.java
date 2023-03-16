package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.data.TeamRepoI;
import com.mycapstone.masonsoccer.data.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import com.mycapstone.masonsoccer.service.CoachService;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author nesibe karatas
 */

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/teams")
public class TeamController {
    @Autowired
    CoachService coachService;

    CoachRepoI coachRepoI;
    TeamRepoI teamRepoI;

    TeamService teamService;
    Team team;
    Training training;
    TrainingService trainingService;
    TrainingRepoI trainingRepoI;
    @Autowired
    public TeamController(CoachService coachService, CoachRepoI coachRepoI, TeamRepoI teamRepoI, TeamService teamService, TrainingService trainingService, TrainingRepoI trainingRepoI) {
        this.coachService = coachService;
        this.coachRepoI = coachRepoI;
        this.teamRepoI = teamRepoI;
        this.teamService = teamService;
        this.trainingService = trainingService;
        this.trainingRepoI = trainingRepoI;
    }

    @GetMapping()
    public String teamsList(Model model, HttpServletRequest request) throws Exception{
        log.debug("teams listed");
        Coach coach= null;
        Principal p =request.getUserPrincipal();
        if(p != null){
            coach = coachRepoI.findByEmail(p.getName()).get();
        }
        model.addAttribute("coach", coach);
        model.addAttribute("teams", teamService.findAll());
        return "teams";
    }

    @GetMapping("/teamsplayers")
    public String viewTeamsPlayers(@RequestParam("id") Integer id, Model model){
        log.warn("viewPlayers with team id: "+id);
        model.addAttribute("team", teamService.findTeamById(id));
        model.addAttribute("players", teamService.findTeamPlayers(id));
        return "teamsplayers";
    }


    @GetMapping("/addteam")
    public String addTeamForm(@ModelAttribute("team") Team team, Model model){
        log.debug("addTeamForm invoked");
        model.addAttribute("team", new Team());
        List<String> genderTypes=Arrays.asList("Girl", "boy", "NA");
        model.addAttribute("genderTypes", genderTypes);
        List<Coach> listOfCoaches= coachService.findAll();
        model.addAttribute("listOfCoaches", listOfCoaches);
        return "addteam";
    }


    @PostMapping("/addteam/save")
    public String saveTeam(@ModelAttribute("team") Team team, Coach coach,Model model){
        model.addAttribute("team", teamService.saveOrUpdateTeam(team, coach));
        return "redirect:/teams";
    }

    @GetMapping("/deleteTeam/{id}")
    public String deleteTeam(@PathVariable(name="id") Integer id, Model model) throws Exception{
        log.debug("Value of the id "+String.valueOf(id));
        Team team = teamService.findTeamById(id);
        String coachName= team.getCoach().getFirstName();
        Optional<Coach> coach= coachService.findByFirstName(coachName);
        coachService.removeTeam(coach.get().getId(),team);
        teamService.deleteTeam(team);
        return "redirect:/teams";
    }
}










