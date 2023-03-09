package com.mycapstone.masonsoccer.models;

/**
 * @author nesibe karatas
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NonNull
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Size(max = 50)
    @NonNull
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NonNull
    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    Parent parent;

    public Player(@NonNull String firstName, @NonNull String lastName, @NonNull String dateOfBirth, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
    }

    public Player(@NonNull String firstName, @NonNull String lastName, @NonNull String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) && firstName.equals(player.firstName) && lastName.equals(player.lastName) && dateOfBirth.equals(player.dateOfBirth) && Objects.equals(team, player.team) && Objects.equals(parent, player.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, team, parent);
    }
}
