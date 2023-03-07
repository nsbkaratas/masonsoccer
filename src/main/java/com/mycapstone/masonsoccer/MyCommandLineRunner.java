package com.mycapstone.masonsoccer;

import com.mycapstone.masonsoccer.dao.CoachRepoI;
import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Team;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.temporal.Temporal;

/**
 * @author nesibe karatas
 */
@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyCommandLineRunner implements CommandLineRunner {


    TeamRepoI teamRepoI;
    CoachRepoI coachRepoI;

    @Autowired
    public MyCommandLineRunner(TeamRepoI teamRepoI, CoachRepoI coachRepoI) {
        this.teamRepoI = teamRepoI;
        this.coachRepoI = coachRepoI;
    }


    @PostConstruct
    public void init(){
        log.debug("==== My Command Line Runner ====");
    }

    @Override
    public void run(String... args) throws Exception {

        Coach coach1 = new Coach("Yalcin","Karatas","yk@ex.com","123456677","yzk","password");
        Coach coach2 = new Coach("Safiye", "Karatas", "sk@ex.com", "23454","sek","password");

        Team team1 = new Team( "Kickers", "3 to 5", "NA");
        Team team2 = new Team("Crickets","5 to 7","Girls" );
        Team team3 = new Team("Wildcats","7 to 10","Girls" );
        Team team4 = new Team("Tigers","5 to 7","Boys" );
        Team team5 = new Team("Lions","7 to 10","Boys" );

//        coach1.getTeam().add(team1);
//        coach1.getTeam().add(team2);
//        coach2.getTeam().add(team3);
//        coach2.getTeam().add(team4);
//        coach1.getTeam().add(team5);


        this.coachRepoI.save(coach1);
        this.coachRepoI.save(coach2);




    }

}
