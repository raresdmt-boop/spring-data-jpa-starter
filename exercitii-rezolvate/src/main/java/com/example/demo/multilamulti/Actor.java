package com.example.demo.multilamulti;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "actor_film",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private Set<Film> films = new HashSet<>();

    protected Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public void addFilm(Film film) {
        films.add(film);
        film.getActors().add(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Film> getFilms() {
        return films;
    }
}
