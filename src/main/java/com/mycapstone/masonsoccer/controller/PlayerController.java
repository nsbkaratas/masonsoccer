package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.data.PlayerRepoI;
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
import org.springframework.web.servlet.ModelAndView;

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
    private final PlayerRepoI playerRepoI;
    private TeamRepoI teamRepoI;

    CoachService coachService;

    private final CoachRepoI coachRepoI;

    TeamService teamService;

    PlayerService playerService;
    @Autowired
    public PlayerController(TeamRepoI teamRepoI, CoachService coachService, CoachRepoI coachRepoI, TeamService teamService, PlayerService playerService,
                            PlayerRepoI playerRepoI) {
        this.teamRepoI = teamRepoI;
        this.coachService = coachService;
        this.coachRepoI = coachRepoI;
        this.teamService = teamService;
        this.playerService = playerService;
        this.playerRepoI = playerRepoI;
    }
//        Show Players list
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

//    delete player

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

//    show add player form
    @GetMapping("/addplayer")
    public String addPlayerForm(@ModelAttribute("player") Player player,
                                Model model){
        log.warn("addplayer form displayed");
        model.addAttribute("player", new Player());
        List<String> genderTypes= Arrays.asList("Girl", "boy", "NA");
        model.addAttribute("genderTypes", genderTypes);
        List<Team> teams = teamService.findAll();
        model.addAttribute("teams", teams);
        return "addplayer";
    }

//    save player

    @PostMapping("/addplayer/save")
    public String savePlayer(@Valid @ModelAttribute("player") Player player, BindingResult bindingResultPlayer,
                             Model model,
                             Team team){
        if(bindingResultPlayer.hasErrors()){
            log.warn("errors with bindingresult player" );
            return "addplayer";
        }else {
            model.addAttribute("player", playerService.saveOrUpdatePlayer(player, team));
            model.addAttribute("message", "Successfully added player");
            return "redirect:/players";
        }
    }

//    @GetMapping("/showUpdateForm")
//    public ModelAndView showUpdateForm(@RequestParam int id) {
//        ModelAndView mv = new ModelAndView("AddPlayer");
//        Player p = playerRepoI.findById(id).get();
//        log.warn(p.toString()+"player is updated");
//        mv.addObject("player", p);
//        return mv;
//
//    }
//
//    @GetMapping("/playerform")
//    public ModelAndView playerUpdateForm() {
//        ModelAndView mv = new ModelAndView("AddPlayer");
//        Player p = new Player();
//        mv.addObject("player", p);
//        return mv;
//
//    }



//    @GetMapping("/addplayer")
//    public String addPlayerForm(@PathVariable("id") Integer id,
//                                @ModelAttribute("player") Player player,
//                                Model model){
//        log.warn("addplayer form displayed");
//        if(playerRepoI.findById(id).isPresent()){
//        List<String> genderTypes= Arrays.asList("Girl", "boy", "NA");
//        model.addAttribute("genderTypes", genderTypes);
//        List<Team> teams = teamService.findAll();
//        model.addAttribute("teams", teams);
//        }else {
//            model.addAttribute("player", new Player());
//            List<String> genderTypes= Arrays.asList("Girl", "boy", "NA");
//            model.addAttribute("genderTypes", genderTypes);
//            List<Team> teams = teamService.findAll();
//            model.addAttribute("teams", teams);
//        }
//
//        return "addplayer";
//    }

//    @PostMapping("updateplayer/{id}")
//    public String updatePlayer(@PathVariable("id") Integer id,
//                               @ModelAttribute("team") Team team,
//                               @Valid @ModelAttribute("player") Player player, BindingResult bindingResult, Model model) throws Exception{
//        if(bindingResult.hasErrors()){
//            log.warn("errors with bindingresult player" );
//            return "updateplayer";
//        }
//
//        Player player1 = playerService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid player Id:" + id));
//        player1.setFirstName(player.getFirstName());
//        player1.setLastName(player.getLastName());
//        player1.setDateOfBirth(player.getDateOfBirth());
//        player1.setGender(player.getGender());
//        player1.setTeam(team);
//        Player p = playerRepoI.save(player1);
////        playerService.saveOrUpdatePlayer(player,team);
//        model.addAttribute("player", p);
//        return "redirect:/players";
//
//    }



//    @GetMapping("/update/{id}")
//    public String updatePlayerForm(@PathVariable("id") Integer id,
//                                   Model model) throws Exception{
//        Player player = playerService.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("Invalid player Id:" + id));
//        model.addAttribute("player", player);
//        LocalDate dob = player.getDateOfBirth();
//        model.addAttribute("dob",dob);
//        String gender = player.getGender();
//        model.addAttribute("gender", gender);
//        List<Team> teams = teamService.findAll();
//        model.addAttribute("teams", teams);
//
//        return "updateplayer";
//    }


}