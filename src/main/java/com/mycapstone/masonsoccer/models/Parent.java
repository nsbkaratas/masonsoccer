package com.mycapstone.masonsoccer.models;

import jakarta.persistence.ManyToOne;

import java.util.List;

/**
 * @author nesibe karatas
 */

public class Parent {
    Long id;
    String firstName;
    String email;

    @ManyToOne()
    List<Player> players;
}
