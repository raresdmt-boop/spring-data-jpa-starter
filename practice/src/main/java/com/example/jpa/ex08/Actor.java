package com.example.jpa.ex08;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // TODO ex08.1: Actor e OWNING side al relației N—N. Adaugă:
    //    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    //    @JoinTable(name = "actor_film",
    //        joinColumns = @JoinColumn(name = "actor_id"),
    //        inverseJoinColumns = @JoinColumn(name = "film_id"))
    //  Best practice: N—N se ține cu un tabel de legătură (@JoinTable), colecția e
    //  Set (nu List) și NU pui CascadeType.REMOVE (ai șterge filme partajate).
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "actor_film",
    joinColumns = @JoinColumn(name = "actor.id"),
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
