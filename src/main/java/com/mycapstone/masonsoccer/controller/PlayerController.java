package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.data.TeamRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.service.CoachService;
import com.mycapstone.masonsoccer.service.PlayerService;
import com.mycapstone.masonsoccer.service.TeamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private TeamRepoI teamRepoI;

    CoachService coachService;

    private final CoachRepoI coachRepoI;

    TeamService teamService;

    PlayerService playerService;
    @Autowired
    public PlayerController(TeamRepoI teamRepoI, CoachService coachService, CoachRepoI coachRepoI, TeamService teamService, PlayerService playerService) {
        this.teamRepoI = teamRepoI;
        this.coachService = coachService;
        this.coachRepoI = coachRepoI;
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping()
    public String players(Model model, HttpServletRequest request){
        log.warn("I am in players controller method");
        Coach coach= null;
        Principal p =request.getUserPrincipal();
        if(p != null){
            coach = coachRepoI.findByEmail(p.getName()).get();
        }
        model.addAttribute("coach", coach);
        model.addAttribute("players",playerService.showPlayers());
        return "players";
    }


    @GetMapping("/deleteplayer/{id}")
    public String deletePlayer(@PathVariable(name="id")Integer id,  Model model) throws Exception{
        log.warn("delete player in player controller is invoked");
       Player player = playerService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));
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
//            LocalDate dob = player.getDateOfBirth();
//            LocalDate now = LocalDate.now();
//            Period period = Period.between(dob, now);
//            int age = period.getYears();
////            if(age==3||age==5 && player.getGender()=="NA"){
////
////            }
            model.addAttribute("player", playerService.saveOrUpdatePlayer(player,team));
            model.addAttribute("message", "Successfully added player");
            return "redirect:/players";
        }
    }
    @GetMapping("/update/{id}")
    public String updatePlayerForm(@PathVariable("id") Integer id,
                                   Model model) throws Exception{
        Player player = playerService.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid player Id:" + id));
        model.addAttribute("player", player);
        LocalDate dob = player.getDateOfBirth();
        model.addAttribute("dob",dob);
        String gender = player.getGender();
        model.addAttribute("gender", gender);
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);

        return "updateplayer";
    }

    @PostMapping("updateplayer/{id}")
    public String updatePlayer(@PathVariable("id") Integer id,
                               @RequestParam("teamId") Integer teamId,
                               @Valid @ModelAttribute("player") Player player, BindingResult bindingResult, Model model) throws Exception{
        if(bindingResult.hasErrors()){
            log.warn("errors with bindingresult player" );
            return "updateplayer";
        }

        Player player1 = playerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));
        Team team = teamService.findTeamById(teamId);
        player1.setFirstName(player.getFirstName());
        player1.setLastName(player.getLastName());
        player1.setDateOfBirth(player.getDateOfBirth());
        player1.setGender(player.getGender());
        player1.setTeam(team);
        playerService.saveOrUpdatePlayer(player,team);
        model.addAttribute("player", player);
        return "redirect:/players";

    }

}