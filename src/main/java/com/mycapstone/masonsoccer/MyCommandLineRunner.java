package com.mycapstone.masonsoccer;

import com.mycapstone.masonsoccer.dao.CoachRepoI;
import com.mycapstone.masonsoccer.dao.PlayerRepoI;
import com.mycapstone.masonsoccer.dao.TeamRepoI;
import com.mycapstone.masonsoccer.dao.TrainingRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import com.mycapstone.masonsoccer.models.Training;
import com.mycapstone.masonsoccer.service.PlayerService;
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

//        Coach coach1 = new Coach("Yalcin","Karatas","yk@ex.com","123456677","yzk","password");
//        Coach coach2 = new Coach("Safiye", "Karatas", "sk@ex.com", "23454","sek","password");




    PlayerService playerService;
    PlayerRepoI playerRepoI;

    TeamRepoI teamRepoI;
    CoachRepoI coachRepoI;
    TrainingRepoI trainingRepoI;
    TeamService teamService;
    TrainingService trainingService;
    @Autowired
    public MyCommandLineRunner(PlayerService playerService, PlayerRepoI playerRepoI, TeamRepoI teamRepoI, CoachRepoI coachRepoI, TrainingRepoI trainingRepoI, TeamService teamService, TrainingService trainingService) {
        this.playerService = playerService;
        this.playerRepoI = playerRepoI;
        this.teamRepoI = teamRepoI;
        this.coachRepoI = coachRepoI;
        this.trainingRepoI = trainingRepoI;
        this.teamService = teamService;
        this.trainingService = trainingService;
    }

    @PostConstruct
    public void init(){

        log.debug("==== My Command Line Runner ====");
    }

    @Override
    public void run(String... args) throws Exception {


        Coach coach1 = new Coach("José","Mourinho","jm@ex.com","123456677","mourinho","password1");
        coachRepoI.saveAndFlush(coach1);
        Coach coach2 = new Coach("Alex", "Ferguson", "af@ex.com", "23454","ferguson","password2");
        coachRepoI.saveAndFlush(coach2);
        Coach coach3 = new Coach("Marcello","Lippi","ml@ex.com","123456677","lippi","password3");
        coachRepoI.saveAndFlush(coach3);
        Coach coach4 = new Coach("Arsène","Wenger","aw@ex.com","123456677","wenger","password4");
        coachRepoI.saveAndFlush(coach4);
        Coach coach5 = new Coach("Yalcin","Karatas","yk@ex.com","123456677","karatas","password5");
        coachRepoI.saveAndFlush(coach5);

        Team team1 = new Team( "Kickers", "3 to 5", "NA");
        teamRepoI.saveAndFlush(team1);
        Team team2 = new Team("Crickets","5 to 7","Girls" );
        teamRepoI.saveAndFlush(team2);
        Team team3 = new Team("Wildcats","7 to 10","Girls" );
        teamRepoI.saveAndFlush(team3);
        Team team4 = new Team("Tigers","5 to 7","Boys" );
        teamRepoI.saveAndFlush(team4);
        Team team5 = new Team("Lions","7 to 10","Boys" );
        teamRepoI.saveAndFlush(team5);
//        Team team1 = new Team( "Kickers", "3 to 5");
//        teamRepoI.saveAndFlush(team1);
//        Team team2 = new Team("Crickets","5 to 7");
//        teamRepoI.saveAndFlush(team2);
//        Team team3 = new Team("Wildcats","7 to 10");
//        teamRepoI.saveAndFlush(team3);
//        Team team4 = new Team("Tigers","5 to 7");
//        teamRepoI.saveAndFlush(team4);
//        Team team5 = new Team("Lions","7 to 10");
//        teamRepoI.saveAndFlush(team5);

        Training training1 = new Training("12-12-2022","1", "1","Mason", team1);
        trainingRepoI.saveAndFlush(training1);
        Training training2 = new Training("2023-04-17", "12:30", "1 hour", "w2w Soccer", team2);
        trainingRepoI.saveAndFlush(training2);
        Training training3 = new Training("2023-04-18", "12:30", "1 hour", "w2w Soccer", team3);
        trainingRepoI.saveAndFlush(training3);
        Training training4 = new Training("2023-04-19", "12:30", "1 hour", "w2w Soccer",team2);
        trainingRepoI.saveAndFlush(training4);
        Training training5 = new Training("2023-04-15", "12:30", "1 hour", "w2w Soccer", team1);
        trainingRepoI.saveAndFlush(training5);
        Training training6 = new Training("2023-04-05", "12:30", "1 hour", "w2w Soccer", team4);
        trainingRepoI.saveAndFlush(training6);
        Training training7 = new Training("2023-04-06", "12:30", "1 hour", "w2w Soccer", team5);
        trainingRepoI.saveAndFlush(training7);
        Training training8 = new Training("2023-04-06", "12:30", "1 hour", "Mason", team4);
        trainingRepoI.saveAndFlush(training8);
        Training training9 = new Training("2023-04-07", "12:30", "1 hour", "w2w Soccer", team4);
        trainingRepoI.saveAndFlush(training9);
        Training training10 = new Training("2023-04-08", "12:30", "1 hour", "w2w Soccer",team3);
        trainingRepoI.saveAndFlush(training10);
        Training training11 = new Training("2023-04-09", "12:30", "1 hour", "w2w Soccer",team2);
        trainingRepoI.saveAndFlush(training11);

//        Player player1 = new Player("Safiye", "Karatas","03-07-2013",team3 );
        Player player1 = new Player("Cristiano", "Ronaldo", "05-02-2018", team1);
        playerRepoI.saveAndFlush(player1);
        Player player2 = new Player("Karim", "Benzema", "19-12-2019", team1);
        playerRepoI.saveAndFlush(player2);
        Player player3 = new Player("Emily", "Wilson", "30-03-2018", team1);
        playerRepoI.saveAndFlush(player3);
        Player player4 = new Player("Gareth", "Bale", "16-07-2018", team1);
        playerRepoI.saveAndFlush(player4);
        Player player5 = new Player("Sara", "Davis", "04-01-2019", team1);
        playerRepoI.saveAndFlush(player5);
        Player player6 = new Player("Olivia", "Brown", "09-09-2019", team1);
        playerRepoI.saveAndFlush(player6);
        Player player7 = new Player("Eden", "Hazard", "07-01-2018", team1);
        playerRepoI.saveAndFlush(player7);
        Player player8 = new Player("Madison", "Asensio", "21-01-2016", team2);
        playerRepoI.saveAndFlush(player8);
        Player player9 = new Player("Mia", "Alarcon", "21-04-2017", team2);
        playerRepoI.saveAndFlush(player9);
        Player player10 = new Player("Amelia", "Vieira", "12-05-2016", team2);
        playerRepoI.saveAndFlush(player10);
        Player player11 = new Player("Charlotte", "Ferreira", "23-02-2016", team2);
        playerRepoI.saveAndFlush(player11);
        Player player12 = new Player("Ava", "Carvajal", "11-01-2016", team2);
        playerRepoI.saveAndFlush(player12);
        Player player13 = new Player("Eva", "Vazquez", "01-07-2017", team2);
        playerRepoI.saveAndFlush(player13);
        Player player14 = new Player("Lily", "Valverde", "22-07-2017", team2);
        playerRepoI.saveAndFlush(player14);
        Player player15 = new Player("Allison", "Varane", "25-04-2013", team3);
        playerRepoI.saveAndFlush(player15);
        Player player16 = new Player("Grace", "Courtois", "11-05-2015", team3);
        playerRepoI.saveAndFlush(player16);
        Player player17 = new Player("Hailey", "Odriozola", "14-12-2014", team3);
        playerRepoI.saveAndFlush(player17);
        Player player18 = new Player("Anna", "Militao", "18-01-2013", team3);
        playerRepoI.saveAndFlush(player18);
        Player player19 = new Player("Hannah", "Fernandez", "18-01-2015", team3);
        playerRepoI.saveAndFlush(player19);
        Player player20 = new Player("Lexi", "De Bruyne", "28-06-2014", team3);
        playerRepoI.saveAndFlush(player20);
        Player player21 = new Player("Lori", "Sterling", "08-12-2014", team3);
        playerRepoI.saveAndFlush(player21);
        Player player22 = new Player("Sergio", "Aguero", "02-06-2017", team4);
        playerRepoI.saveAndFlush(player22);
        Player player23 = new Player("Ilkay", "Gundogan", "24-10-2017", team4);
        playerRepoI.saveAndFlush(player23);
        Player player24 = new Player("Bernardo", "Silva", "10-08-2016", team4);
        playerRepoI.saveAndFlush(player24);
        Player player25 = new Player("Riyad", "Mahrez", "21-02-2017", team4);
        playerRepoI.saveAndFlush(player25);
        Player player26 = new Player("Fernandinho", "Roza", "04-05-2016", team4);
        playerRepoI.saveAndFlush(player26);
        Player player27 = new Player("Nathan", "Ake", "18-02-2016", team4);
        playerRepoI.saveAndFlush(player27);
        Player player28 = new Player("Phil", "Foden", "28-05-2016", team4);
        playerRepoI.saveAndFlush(player28);
        Player player29 = new Player("Gabriel", "Jesus", "03-04-2013", team5);
        playerRepoI.saveAndFlush(player29);
        Player player30 = new Player("Kyle", "Walker", "28-05-2013", team5);
        playerRepoI.saveAndFlush(player30);
        Player player31 = new Player("Ruben", "Dias", "14-05-2013", team5);
        playerRepoI.saveAndFlush(player31);
        Player player32 = new Player("John", "Stones", "28-05-2015", team5);
        playerRepoI.saveAndFlush(player32);
        Player player33 = new Player("Aymeric", "Laporte", "27-05-2015", team5);
        playerRepoI.saveAndFlush(player33);
        Player player34 = new Player("Benjamin", "Mendy", "17-07-2014", team5);
        playerRepoI.saveAndFlush(player34);
        Player player35 = new Player("Joao", "Cancelo", "27-05-2014", team5);
        playerRepoI.saveAndFlush(player35);



//        Player player36 = new Player("Rodri", "Hernandez", "22-06-1996", team);
//        Player player37 = new Player("Ederson", "Santana", "17-08-1993", team);
//        Player player38 = new Player("Zack", "Steffen", "02-04-1995", team);
//        Player player39 = new Player("Scott", "Carson", "03-09-1985", team);

    }

}
