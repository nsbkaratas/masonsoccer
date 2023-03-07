package com.mycapstone.masonsoccer.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nesibe karatas
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/login")
public class LoginController {
    @GetMapping()
    public String login(){
        log.debug("I am in login controller method");
        return "login";
    }
    @PostMapping("loginsuccess")
    public String loginSuccess(){
        log.debug("I am in loginsuccess controller method");
        return "redirect:/";
    }
}
