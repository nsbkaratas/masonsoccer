package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
@Slf4j
public class MyAdviceController {
    @Autowired
    private final CoachRepoI coachRepoI;

    public MyAdviceController(CoachRepoI coachRepoI) {
        this.coachRepoI = coachRepoI;
    }


    @ModelAttribute
    public void loggedInUser(Model model, HttpServletRequest request, HttpSession http){
        Principal p = request.getUserPrincipal();

        Coach coach = null;
        if(p != null){
            coach =  coachRepoI.findByEmail(p.getName()).get();
            http.setAttribute("currentUser", coach);
            log.warn("MyControllerAdvice: session attr theStudent in advice controller  " + coach.getEmail());

        } else {
            log.warn("MyControllerAdvice: principal was null");
        }
    }

}
