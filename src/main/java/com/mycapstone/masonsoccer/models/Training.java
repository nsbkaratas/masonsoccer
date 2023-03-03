package com.mycapstone.masonsoccer.models;

/**
 * @author nesibe karatas
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id", nullable = false)
    private Integer id;

    @NonNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NonNull
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NonNull
    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @Size(max = 50)
    @NonNull
    @Column(name = "field_name", nullable = false, length = 50)
    private String fieldName;

    @ManyToMany(mappedBy = "trainings",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    Set<Team> team = new LinkedHashSet<>();

    public void addTeam(Team t){
        team.add(t);
        t.getTrainings().add(this);
    }
    public void removeTeam(Team t){
        team.remove(t);
        t.getTrainings().remove(this);
    }

    public Training(@NonNull LocalDate date, @NonNull LocalTime startTime, @NonNull String duration, @NonNull String fieldName) {
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(id, training.id) && date.equals(training.date) && startTime.equals(training.startTime) && duration.equals(training.duration) && fieldName.equals(training.fieldName) && Objects.equals(team, training.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, duration, fieldName, team);
    }
}
