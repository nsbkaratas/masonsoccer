package com.mycapstone.masonsoccer.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nesibe karatas
 */
@Controller
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/about")
public class AboutController {
    @GetMapping()
    public String about(){
        log.debug("I am in about controller method");
        return "about";
    }
}
