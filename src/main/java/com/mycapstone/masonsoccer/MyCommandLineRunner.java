package com.mycapstone.masonsoccer;

import com.mycapstone.masonsoccer.dao.CoachRepoI;
import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import com.mycapstone.masonsoccer.service.TeamService;
import com.mycapstone.masonsoccer.service.TrainingService;
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

//    TeamService teamService;
//
//    TrainingService trainingService;
//    TeamRepoI teamRepoI;
//    TrainingRepoI trainingRepoI;
//
//    @Autowired
//    public MyCommandLineRunner(TeamService teamService, TrainingService trainingService) {
//        this.teamService = teamService;
//        this.trainingService = trainingService;
//    }
//
//    @PostConstruct
//    public void init(){
//        log.debug("==== My Command Line Runner ====");
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Coach coach1 = new Coach("Yalcin","Karatas","yk@ex.com","123456677","yzk","password");
//        Coach coach2 = new Coach("Safiye", "Karatas", "sk@ex.com", "23454","sek","password");
//
//        Team team1 = new Team( "Kickers", "3 to 5", "NA");
//        Team team2 = new Team("Crickets","5 to 7","Girls" );
//        Team team3 = new Team("Wildcats","7 to 10","Girls" );
//        Team team4 = new Team("Tigers","5 to 7","Boys" );
//        Team team5 = new Team("Lions","7 to 10","Boys" );
//
//
//
//        Training training1 = new Training("2023-04-16", "12:30", "1 hour", "w2w Soccer");
//        Training training2 = new Training("2023-04-17", "12:30", "1 hour", "w2w Soccer");
//        Training training3 = new Training("2023-04-18", "12:30", "1 hour", "w2w Soccer");
//        Training training4 = new Training("2023-04-19", "12:30", "1 hour", "w2w Soccer");
//        Training training5 = new Training("2023-04-15", "12:30", "1 hour", "w2w Soccer");
//
//        team1.addTraining(training1);
//        teamRepoI.save(team1);
//
////        teamService.saveOrUpdate(team2);
////        teamService.saveOrUpdate(team3);
////        teamService.saveOrUpdate(team4);
////        teamService.saveOrUpdate(team5);
//
//        trainingService.saveOrUpdate(training1);
//        trainingService.saveOrUpdate(training2);
//        trainingService.saveOrUpdate(training3);
//        trainingService.saveOrUpdate(training4);
//        trainingService.saveOrUpdate(training5);
//
////        trainingRepoI.save(training1);
////        trainingRepoI.save(training2);
////        trainingRepoI.save(training3);
////        trainingRepoI.save(training4);
////        trainingRepoI.save(training5);
//
//
//
//
//
//
////        coach1.getTeam().add(team1);
////        coach1.getTeam().add(team2);
////        coach2.getTeam().add(team3);
////        coach2.getTeam().add(team4);
////        coach1.getTeam().add(team5);
//
//



    TeamRepoI teamRepoI;
    CoachRepoI coachRepoI;
    TrainingRepoI trainingRepoI;

    @Autowired
    public MyCommandLineRunner(TeamRepoI teamRepoI, CoachRepoI coachRepoI,
                               TrainingRepoI trainingRepoI) {
        this.teamRepoI = teamRepoI;
        this.coachRepoI = coachRepoI;
        this.trainingRepoI = trainingRepoI;
    }


    @PostConstruct
    public void init(){
        log.debug("==== My Command Line Runner ====");
    }

    @Override
    public void run(String... args) throws Exception {

//        Coach coach1 = new Coach("Yalcin","Karatas","yk@ex.com","123456677","yzk","password");
//        Coach coach2 = new Coach("Safiye", "Karatas", "sk@ex.com", "23454","sek","password");

        Team team1 = new Team( "Kickers", "3 to 5", "NA");

        Training training = new Training("12-12-2022","1", "1","Mason");

        trainingRepoI.saveAndFlush(training);
        team1.addTraining(training);
        teamRepoI.saveAndFlush(team1);


    }

}
