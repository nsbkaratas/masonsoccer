package com.mycapstone.masonsoccer.models;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;

/**
 * @author nesibe karatas
 */

public class Player {
    Long id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;

    @OneToMany()
    Parent parent;

}
