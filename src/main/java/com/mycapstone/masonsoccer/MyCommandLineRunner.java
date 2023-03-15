package com.mycapstone.masonsoccer;

import com.mycapstone.masonsoccer.data.*;
import com.mycapstone.masonsoccer.models.*;
import com.mycapstone.masonsoccer.service.PlayerService;
import com.mycapstone.masonsoccer.service.TeamService;
import com.mycapstone.masonsoccer.service.TrainingService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author nesibe karatas
 */
@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyCommandLineRunner implements CommandLineRunner {

//        Coach coach1 = new Coach("Yalcin","Karatas","yk@ex.com","123456677","yzk","password");
//        Coach coach2 = new Coach("Safiye", "Karatas", "sk@ex.com", "23454","sek","password");

    TeamRepoI teamRepoI;
    CoachRepoI coachRepoI;
    TrainingRepoI trainingRepoI;

    AuthGroupRepoI authGroupRepoI;

    PlayerRepoI playerRepoI;
    @Autowired
    public MyCommandLineRunner(AuthGroupRepoI authGroupRepoI, PlayerRepoI playerRepoI, TeamRepoI teamRepoI, CoachRepoI coachRepoI, TrainingRepoI trainingRepoI) {
        this.authGroupRepoI = authGroupRepoI;
        this.playerRepoI = playerRepoI;
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


        Coach coach1 = new Coach("José","Mourinho","jose@ex.com","123456677","mourinho",new BCryptPasswordEncoder().encode("password"));
        coachRepoI.saveAndFlush(coach1);
        Coach coach2 = new Coach("Alex", "Ferguson", "alex@ex.com", "23454","ferguson",new BCryptPasswordEncoder().encode("password"));
        coachRepoI.saveAndFlush(coach2);
        Coach coach3 = new Coach("Marcello","Lippi","marcello@ex.com","123456677","lippi",new BCryptPasswordEncoder().encode("password"));
        coachRepoI.saveAndFlush(coach3);
        Coach coach4 = new Coach("Arsène","Wenger","arsene@ex.com","123456677","wenger",new BCryptPasswordEncoder().encode("password"));
        coachRepoI.saveAndFlush(coach4);
        Coach coach5 = new Coach("Yalcin","Karatas","yalcin@ex.com","123456677","karatas",new BCryptPasswordEncoder().encode("password"));
        coachRepoI.saveAndFlush(coach5);

        AuthGroup authGroup1 = new AuthGroup("yalcin@ex.com", "ROLE_ADMIN");
        AuthGroup authGroup6 = new AuthGroup("yalcin@ex.com", "ROLE_COACH");
        AuthGroup authGroup2 = new AuthGroup("jose@ex.com", "ROLE_COACH");
        AuthGroup authGroup3 = new AuthGroup("alex@ex.com", "ROLE_COACH");
        AuthGroup authGroup4 = new AuthGroup("marcello@ex.com", "ROLE_COACH");
        AuthGroup authGroup5 = new AuthGroup("arsene@ex.com", "ROLE_COACH");

        authGroupRepoI.save(authGroup1);
        authGroupRepoI.save(authGroup2);
        authGroupRepoI.save(authGroup3);
        authGroupRepoI.save(authGroup4);
        authGroupRepoI.save(authGroup5);
        authGroupRepoI.save(authGroup6);


        Team team1 = new Team( "Kickers", "3 to 5", "NA",coach1);
        teamRepoI.saveAndFlush(team1);
        Team team2 = new Team("Crickets","5 to 7","Girls",coach2 );
        teamRepoI.saveAndFlush(team2);
        Team team3 = new Team("Wildcats","7 to 10","Girls",coach3 );
        teamRepoI.saveAndFlush(team3);
        Team team4 = new Team("Tigers","5 to 7","Boys", coach4);
        teamRepoI.saveAndFlush(team4);
        Team team5 = new Team("Lions","7 to 10","Boys",coach5 );
        teamRepoI.saveAndFlush(team5);


        Training training1 = new Training(LocalDate.of(2022,12,12), LocalTime.of(9,30),"1 hour","Mason", team1);
        trainingRepoI.saveAndFlush(training1);
        Training training2 = new Training(LocalDate.of(2023,4,17), LocalTime.of(9,30),"1 hour", "w2w Soccer", team2);
        trainingRepoI.saveAndFlush(training2);
        Training training3 = new Training(LocalDate.of(2023,4,18), LocalTime.of(9,30), "1 hour", "w2w Soccer", team3);
        trainingRepoI.saveAndFlush(training3);
        Training training4 = new Training(LocalDate.of(2023,4,19), LocalTime.of(9,30), "1 hour", "w2w Soccer",team2);
        trainingRepoI.saveAndFlush(training4);
        Training training5 = new Training(LocalDate.of(2023,4,15), LocalTime.of(9,30), "1 hour", "w2w Soccer", team1);
        trainingRepoI.saveAndFlush(training5);
        Training training6 = new Training(LocalDate.of(2023,4,5), LocalTime.of(9,30), "1 hour", "w2w Soccer", team4);
        trainingRepoI.saveAndFlush(training6);
        Training training7 = new Training(LocalDate.of(2023,4,5), LocalTime.of(9,30), "1 hour", "w2w Soccer", team5);
        trainingRepoI.saveAndFlush(training7);
        Training training8 = new Training(LocalDate.of(2023,4,4), LocalTime.of(9,30), "1 hour", "Mason", team4);
        trainingRepoI.saveAndFlush(training8);
        Training training9 = new Training(LocalDate.of(2023,4,7), LocalTime.of(9,30), "1 hour", "w2w Soccer", team4);
        trainingRepoI.saveAndFlush(training9);
        Training training10 = new Training(LocalDate.of(2023,4,11), LocalTime.of(9,30), "1 hour", "w2w Soccer",team3);
        trainingRepoI.saveAndFlush(training10);
        Training training11 = new Training(LocalDate.of(2023,4,12), LocalTime.of(9,30), "1 hour", "w2w Soccer",team2);
        trainingRepoI.saveAndFlush(training11);


        Player player1 = new Player("Cristiano", "Ronaldo", LocalDate.of(2018,2,05),"boy", team1);
        playerRepoI.saveAndFlush(player1);
        Player player2 = new Player("Karim", "Benzema", LocalDate.of(2019,1,15),"boy", team1);
        playerRepoI.saveAndFlush(player2);
        Player player3 = new Player("Emily", "Wilson", LocalDate.of(2018,5,15),"girl", team1);
        playerRepoI.saveAndFlush(player3);
        Player player4 = new Player("Gareth", "Bale", LocalDate.of(2018,6,15),"boy", team1);
        playerRepoI.saveAndFlush(player4);
        Player player5 = new Player("Sara", "Davis", LocalDate.of(2019,4,15), "girl",team1);
        playerRepoI.saveAndFlush(player5);
        Player player6 = new Player("Olivia", "Brown", LocalDate.of(2019,9,15),"girl", team1);
        playerRepoI.saveAndFlush(player6);
        Player player7 = new Player("Eden", "Hazard", LocalDate.of(2018,9,15),"boy", team1);
        playerRepoI.saveAndFlush(player7);
        Player player8 = new Player("Madison", "Asensio", LocalDate.of(2016,4,15),"girl", team2);
        playerRepoI.saveAndFlush(player8);
        Player player9 = new Player("Mia", "Alarcon", LocalDate.of(2017,3,15),"girl", team2);
        playerRepoI.saveAndFlush(player9);
        Player player10 = new Player("Amelia", "Vieira", LocalDate.of(2016,6,15),"girl", team2);
        playerRepoI.saveAndFlush(player10);
        Player player11 = new Player("Charlotte", "Ferreira", LocalDate.of(2016,5,15),"girl", team2);
        playerRepoI.saveAndFlush(player11);
        Player player12 = new Player("Ava", "Carvajal", LocalDate.of(2016,9,15),"girl", team2);
        playerRepoI.saveAndFlush(player12);
        Player player13 = new Player("Eva", "Vazquez", LocalDate.of(2017,6,15),"girl", team2);
        playerRepoI.saveAndFlush(player13);
        Player player14 = new Player("Lily", "Valverde", LocalDate.of(2017,7,15),"girl", team2);
        playerRepoI.saveAndFlush(player14);
        Player player15 = new Player("Allison", "Varane", LocalDate.of(2013,4,15),"girl", team3);
        playerRepoI.saveAndFlush(player15);
        Player player16 = new Player("Grace", "Courtois", LocalDate.of(2015,4,15),"girl", team3);
        playerRepoI.saveAndFlush(player16);
        Player player17 = new Player("Hailey", "Odriozola", LocalDate.of(2014,12,15),"girl", team3);
        playerRepoI.saveAndFlush(player17);
        Player player18 = new Player("Anna", "Militao", LocalDate.of(2013,1,15),"girl", team3);
        playerRepoI.saveAndFlush(player18);
        Player player19 = new Player("Hannah", "Fernandez", LocalDate.of(2015,1,15),"girl", team3);
        playerRepoI.saveAndFlush(player19);
        Player player20 = new Player("Lexi", "De Bruyne", LocalDate.of(2014,4,15),"girl", team3);
        playerRepoI.saveAndFlush(player20);
        Player player21 = new Player("Lori", "Sterling", LocalDate.of(2014,7,15),"girl", team3);
        playerRepoI.saveAndFlush(player21);
        Player player22 = new Player("Sergio", "Aguero", LocalDate.of(2017,4,15),"boy", team4);
        playerRepoI.saveAndFlush(player22);
        Player player23 = new Player("Ilkay", "Gundogan", LocalDate.of(2017,3,15),"boy", team4);
        playerRepoI.saveAndFlush(player23);
        Player player24 = new Player("Bernardo", "Silva", LocalDate.of(2016,7,15),"boy",team4);
        playerRepoI.saveAndFlush(player24);
        Player player25 = new Player("Riyad", "Mahrez", LocalDate.of(2017,6,15),"boy", team4);
        playerRepoI.saveAndFlush(player25);
        Player player26 = new Player("Fernandinho", "Roza", LocalDate.of(2016,5,15),"boy", team4);
        playerRepoI.saveAndFlush(player26);
        Player player27 = new Player("Nathan", "Ake", LocalDate.of(2016,4,15),"boy", team4);
        playerRepoI.saveAndFlush(player27);
        Player player28 = new Player("Phil", "Foden", LocalDate.of(2016,6,15),"boy", team4);
        playerRepoI.saveAndFlush(player28);
        Player player29 = new Player("Gabriel", "Jesus", LocalDate.of(2013,3,15),"boy", team5);
        playerRepoI.saveAndFlush(player29);
        Player player30 = new Player("Kyle", "Walker", LocalDate.of(2013,4,15),"boy", team5);
        playerRepoI.saveAndFlush(player30);
        Player player31 = new Player("Ruben", "Dias", LocalDate.of(2013,7,15),"boy", team5);
        playerRepoI.saveAndFlush(player31);
        Player player32 = new Player("John", "Stones", LocalDate.of(2015,6,15),"boy", team5);
        playerRepoI.saveAndFlush(player32);
        Player player33 = new Player("Aymeric", "Laporte", LocalDate.of(2015,5,15),"boy", team5);
        playerRepoI.saveAndFlush(player33);
        Player player34 = new Player("Benjamin", "Mendy", LocalDate.of(2014,4,15),"boy", team5);
        playerRepoI.saveAndFlush(player34);
        Player player35 = new Player("Joao", "Cancelo", LocalDate.of(2014,5,15),"boy", team5);
        playerRepoI.saveAndFlush(player35);



//        Player player36 = new Player("Rodri", "Hernandez", "22-06-1996", team);
//        Player player37 = new Player("Ederson", "Santana", "17-08-1993", team);
//        Player player38 = new Player("Zack", "Steffen", "02-04-1995", team);
//        Player player39 = new Player("Scott", "Carson", "03-09-1985", team);

    }

}
