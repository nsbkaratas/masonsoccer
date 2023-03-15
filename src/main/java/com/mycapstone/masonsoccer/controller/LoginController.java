package com.mycapstone.masonsoccer.controller;

import com.mycapstone.masonsoccer.models.Coach;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nesibe karatas
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping
public class LoginController {
    @GetMapping("/login")
    public String login(Model model){
        log.debug("I am in login controller method");
        model.addAttribute("coach", new Coach());
        return "login";
    }

    @GetMapping("/403")
    public String access(){
        log.debug("I am in login controller method");
        return "403";
    }
    @GetMapping("/error")
    public String erroraccess(){
        log.debug("I am in login controller method");
        return "error";
    }
//    @PostMapping("processing")
//    public String loginSuccess(){
//        log.debug("I am in loginsuccess controller method");
//        return "redirect:/";
//    }
}
