package com.mycapstone.masonsoccer.models;

/**
 * @author nesibe karatas
 */
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @NonNull
    private String firstName;

    @Size(max = 50)
    @NonNull
    private String lastName;

    @Size(max = 100)
    @NonNull
    private String email;

    @Size(max = 20)
    @NonNull
    private String phoneNumber;

    @Size(max = 50)
    @NonNull
    private String username;

    @Size(max = 50)
    @NonNull
    private String password;
//
//    @ToString.Exclude
//    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    Set<Team> teams = new LinkedHashSet<>() ;
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    Set<Team> team = new LinkedHashSet<>();

//    public void addTeam(Team t){
//        team.add(t);
//        t.setCoach(this);
//    }
//    public void removeTeam(Team t){
//        team.remove(t);
//        t.setCoach(null);
//    }
    public Coach(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String phoneNumber, @NonNull String username, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id) && firstName.equals(coach.firstName) && lastName.equals(coach.lastName) && email.equals(coach.email) && phoneNumber.equals(coach.phoneNumber) && username.equals(coach.username) && password.equals(coach.password) && Objects.equals(team, coach.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, username, password, team);
    }
}
