package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.dao.CoachRepoI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author nesibe karatas
 */

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private CoachRepoI coachRepoI;

    @GetMapping(value = {"/", "/home"})
    public String home(){
        return "home";
    }
    @GetMapping("/register")
    public String register(Model model){

        return "register";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/programs")
    public String programs(){
        return "programs";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
