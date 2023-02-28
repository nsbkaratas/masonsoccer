package com.mycapstone.masonsoccer.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author nesibe karatas
 */
@Entity
@Table(name = "coaches")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Coach {
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer coach_id;

    @NonNull
    String first_name;

    @NonNull
    String last_name;

    @NonNull
    String email;

    @NonNull
    String phone_number;

    @NonNull
    String user_name;

    @NonNull
    String password;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return coach_id.equals(coach.coach_id) && first_name.equals(coach.first_name) && last_name.equals(coach.last_name) && email.equals(coach.email) && phone_number.equals(coach.phone_number) && user_name.equals(coach.user_name) && password.equals(coach.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coach_id, first_name, last_name, email, phone_number, user_name, password);
    }
}
