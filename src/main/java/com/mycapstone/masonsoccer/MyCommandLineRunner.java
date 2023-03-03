package com.mycapstone.masonsoccer;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author nesibe karatas
 */
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {

    @PostConstruct
    public void init(){
        log.debug("==== My Command Line Runner ====");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
