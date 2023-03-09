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
@AllArgsConstructor
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
    private String date;

    @NonNull
    @Column(name = "start_time", nullable = false)
    private String startTime;

    @NonNull
    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @Size(max = 50)
    @NonNull
    @Column(name = "field_name", nullable = false, length = 50)
    private String fieldName;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="team_id")
    Team team;

    public Training(@NonNull String date, @NonNull String startTime, @NonNull String duration, @NonNull String fieldName) {
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.fieldName = fieldName;
    }

    public Training(@NonNull String date, @NonNull String startTime, @NonNull String duration, @NonNull String fieldName, Team team) {
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.fieldName = fieldName;
        this.team = team;
    }



//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Training training = (Training) o;
//        return Objects.equals(id, training.id) && date.equals(training.date) && startTime.equals(training.startTime) && duration.equals(training.duration) && fieldName.equals(training.fieldName) && Objects.equals(team, training.team);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, date, startTime, duration, fieldName, team);
//    }
}
