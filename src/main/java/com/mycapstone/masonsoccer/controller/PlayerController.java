package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.TeamRepoI;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.service.PlayerService;
import com.mycapstone.masonsoccer.service.TeamService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author nesibe karatas
 */

@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private TeamRepoI teamRepoI;

    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;
    @GetMapping()
    public String players(Model model){
        log.warn("I am in players controller method");
        model.addAttribute("players",playerService.showPlayers());
        return "players";
    }


    @GetMapping("/deleteplayer/{id}")
    public String deletePlayer(@PathVariable(name="id")Integer id,  Model model) throws Exception{
        log.warn("delete player in player controller is invoked");
       Player player = playerService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid appoinment Id:" + id));
       String teamName=player.getTeam().getName();
       log.warn("teamname is "+ teamName);
       Optional<Team> team=teamService.findByName(teamName);
       teamService.removePlayer(team.get().getId(), player);
       playerService.deletePlayer(player);
        return "redirect:/players";
    }

    @GetMapping("/addplayer")
    public String addPlayerForm(@ModelAttribute("player") Player player, Model model){
        log.warn("addplayer form displayed");
        model.addAttribute("player", new Player());
        List<String> genderTypes= Arrays.asList("Girl", "boy", "NA");
        model.addAttribute("genderTypes", genderTypes);
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "addplayer";
    }
    @PostMapping("/addplayer/save")
    public String savePlayer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResultPlayer,
                             Model model,
                             Team team){
        if(bindingResultPlayer.hasErrors()){
            log.warn("errors with bindingresult player" );
            return "addplayer";
        }else{
            LocalDate dob = player.getDateOfBirth();
            LocalDate now = LocalDate.now();
            Period period = Period.between(dob, now);
            int age = period.getYears();
//            if(age==3||age==5 && player.getGender()=="NA"){
//
//            }
            model.addAttribute("player", playerService.saveOrUpdatePlayer(player,team));
            model.addAttribute("message", "Successfully added player");
            return "redirect:/players";
        }


    }

}