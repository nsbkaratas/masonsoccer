package com.mycapstone.masonsoccer.service;

import com.mycapstone.masonsoccer.dao.CoachRepoI;
import com.mycapstone.masonsoccer.models.Coach;
import com.mycapstone.masonsoccer.models.Player;
import com.mycapstone.masonsoccer.models.Team;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoachService {
    private final CoachRepoI coachRepoI;

    public CoachService(CoachRepoI coachRepoI) {
        this.coachRepoI = coachRepoI;
    }

    //find all coaches
    public List<Coach> findAll() {
       return coachRepoI.findAll();
    }

}
