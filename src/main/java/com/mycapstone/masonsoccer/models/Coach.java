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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @NonNull @Setter(AccessLevel.NONE)
    private String password;

//    @ToString.Exclude
//    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    Set<Team> teams = new LinkedHashSet<>() ;
    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    Set<Team> team = new LinkedHashSet<>();

    public void addTeam(Team t){
        team.add(t);
        t.setCoach(this);
    }
    public void removeTeam(Team t){
        team.remove(t);
        t.setCoach(null);
    }
    public Coach(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String phoneNumber, @NonNull String username, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }
    public String setPassword(String password) {
       return this.password = new BCryptPasswordEncoder(4).encode(password);
    }

    public Coach(Integer id, @NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String phoneNumber, @NonNull String username, @NonNull String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = setPassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return firstName.equals(coach.firstName) && lastName.equals(coach.lastName) && email.equals(coach.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
