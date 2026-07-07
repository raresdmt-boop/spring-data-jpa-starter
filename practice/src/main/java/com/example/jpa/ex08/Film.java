package com.example.jpa.ex08;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // TODO ex08.2: Film e INVERSE side. Adaugă @ManyToMany(mappedBy = "films").
    //  `mappedBy` spune că tabelul de legătură e deja definit de câmpul `films`
    //  din Actor — nu-l redefini aici.
    private Set<Actor> actors = new HashSet<>();

    protected Film() {
    }

    public Film(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Actor> getActors() {
        return actors;
    }
}
