package com.mycapstone.masonsoccer.models;

/**
 * @author nesibe karatas
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
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
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 2, message = "at least 2 characters required")
    private String firstName;

    @NotEmpty
    @Size(min = 2, message = "at least 2 characters required")
    private String lastName;
    @Size(max = 10)
    private String gender;

    private LocalDate dateOfBirth;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    public Player() {
    }

//    @ToString.Exclude
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "parent_id")
//    Parent parent;

    public Player( String firstName,  String lastName,  LocalDate dateOfBirth, String gender, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender=gender;
        this.team = team;
    }

    public Player(String firstName,String lastName,  LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return firstName.equals(player.firstName) && lastName.equals(player.lastName) && dateOfBirth.equals(player.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }
}
