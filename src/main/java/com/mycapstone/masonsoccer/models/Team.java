package com.mycapstone.masonsoccer.models;

/**
 * @author nesibe karatas
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NonNull
    @Column(nullable = false, length = 50)
    private String name;

    @Size(max = 50)
    @NonNull
    @Column(nullable = false, length = 50)
    private String ageGroup;

    @Size(max = 10)
    @NonNull
    @Column( nullable = false, length = 10)
    private String gender;

//    @ToString.Exclude
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
//    @JoinColumn (name = "coach_id")
////    @JsonIgnore
//    Coach coach;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Coach.class)
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    Coach coach;

    @ToString.Exclude
    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<Training> trainings = new HashSet<>();



//    @OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
//    @JoinTable(name = "team_training",
//        joinColumns = @JoinColumn(name = "team_id"),
//        inverseJoinColumns = @JoinColumn(name = "training_id"))
//    Set<Training> trainings= new LinkedHashSet<>();

    public void addTraining(Training t){
        trainings.add(t);
        t.setTeam(this);
    }

    public void removeTraining(Training t){
        trainings.remove(t);
        t.setTeam(null);
    }

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    Set<Player> players = new LinkedHashSet<>();

    public void addPlayer(Player p){
        players.add(p);
        p.setTeam(this);
    }

    public Team() {
    }

    public void removePlayer(Player p){
        players.remove(p);
        p.setTeam(null);
    }

    public Team(@NonNull String name, @NonNull String ageGroup, @NonNull String gender, Coach coach) {
        this.name = name;
        this.ageGroup = ageGroup;
        this.gender = gender;
        this.coach = coach;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Team team = (Team) o;
//        return Objects.equals(id, team.id) && name.equals(team.name) && ageGroup.equals(team.ageGroup) && gender.equals(team.gender) && Objects.equals(coach, team.coach) && Objects.equals(trainings, team.trainings) && Objects.equals(players, team.players);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, ageGroup, gender, coach, trainings, players);
//    }
}
