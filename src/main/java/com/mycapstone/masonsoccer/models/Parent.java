package com.mycapstone.masonsoccer.models;

/**
 * @author nesibe karatas
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.security.PublicKey;
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
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NonNull
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Size(max = 50)
    @NonNull
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Size(max = 100)
    @NonNull
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Size(max = 20)
    @NonNull
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Size(max = 100)
    @NonNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 50)
    @NonNull
    @Column(name = "password", nullable = false, length = 50)
    private String password;

//    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
//    Set<Player> players = new LinkedHashSet<>();
//
//    public void addPlayer(Player p){
//        players.add(p);
//        p.setParent(this);
//    }
//    public void removePlayer(Player p){
//        players.remove(p);
//        p.setParent(null);
//    }

    public Parent(@NonNull String firstName, @NonNull String lastName, @NonNull String address, @NonNull String phoneNumber, @NonNull String email, @NonNull String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Parent parent = (Parent) o;
//        return Objects.equals(id, parent.id) && firstName.equals(parent.firstName) && lastName.equals(parent.lastName) && address.equals(parent.address) && phoneNumber.equals(parent.phoneNumber) && email.equals(parent.email) && password.equals(parent.password) && Objects.equals(players, parent.players);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, address, phoneNumber, email, password, players);
//    }
}
