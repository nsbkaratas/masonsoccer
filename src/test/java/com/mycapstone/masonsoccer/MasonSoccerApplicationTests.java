package com.mycapstone.masonsoccer;

import com.mycapstone.masonsoccer.data.CoachRepoI;
import com.mycapstone.masonsoccer.data.PlayerRepoI;
import com.mycapstone.masonsoccer.data.TeamRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MasonSoccerApplicationTests {

	TeamRepoI teamRepoI;
	CoachRepoI coachRepoI;
	PlayerRepoI playerRepoI;

	@Autowired
	public MasonSoccerApplicationTests(TeamRepoI teamRepoI, CoachRepoI coachRepoI, PlayerRepoI playerRepoI) {
		this.teamRepoI = teamRepoI;
		this.coachRepoI = coachRepoI;
		this.playerRepoI = playerRepoI;
	}

	Coach coach1 = new Coach("José","Mourinho","jm@ex.com","123456677","mourinho","password1");
	Team team1 = new Team( "Kickers", "3 to 5", "NA");
	Player player1 = new Player("Cristiano", "Ronaldo", LocalDate.of(2018,2,05),"boy", team1);

	@Test
	void contextLoads() {
		assertThat(coachRepoI.findByEmail("jm@ex.com").get()).isEqualTo(coach1);
	}

	@Test
	void contextLoadsNew(){
		assertThat(playerRepoI.findByFirstNameAndLastName("Cristiano", "Ronaldo").get()).isEqualTo(player1);
	}
	@Test
	void findByNameAllIgnoreCaseTest() {
		if(teamRepoI.findByNameAllIgnoreCase("Crickets").isPresent()){
			Team team = teamRepoI.findByNameAllIgnoreCase("Crickets").get();
			assertTrue(true);
		}
	}



}
