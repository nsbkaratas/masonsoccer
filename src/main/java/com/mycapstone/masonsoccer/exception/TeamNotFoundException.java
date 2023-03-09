package com.mycapstone.masonsoccer.exception;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer id) {
        super("Team with " + id + " does not exist ");
    }
}
