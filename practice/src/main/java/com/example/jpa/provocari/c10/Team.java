package com.example.jpa.provocari.c10;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Athlete> athletes = new ArrayList<>();

    protected Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public void addAthlete(Athlete athlete) {
        athletes.add(athlete);
        athlete.setTeam(this);
    }

    public void removeAthlete(Athlete athlete) {
        athletes.remove(athlete);
        athlete.setTeam(null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }
}
