package com.example.demo.multilamulti;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DemoExercise;

@Component
public class MultiLaMultiExercise implements DemoExercise {

    private final ActorRepository actors;

    private final FilmRepository films;

    public MultiLaMultiExercise(ActorRepository actors, FilmRepository films) {
        this.actors = actors;
        this.films = films;
    }

    @Override
    public String key() {
        return "multilamulti";
    }

    @Override
    public String title() {
        return "@ManyToMany (*-*): un actor joaca in mai multe filme, un film are mai multi actori";
    }

    @Override
    @Transactional
    public void run() {
        actors.deleteAll();
        films.deleteAll();

        Film matrix = new Film("The Matrix");
        Film johnWick = new Film("John Wick");
        Film speed = new Film("Speed");

        Actor keanu = new Actor("Keanu Reeves");
        keanu.addFilm(matrix);
        keanu.addFilm(johnWick);
        keanu.addFilm(speed);

        Actor laurence = new Actor("Laurence Fishburne");
        laurence.addFilm(matrix);

        actors.saveAll(List.of(keanu, laurence));

        System.out.println("Cascade PERSIST: am salvat actorii, filmele s-au salvat automat prin @JoinTable.");
        System.out.println("Actori: " + actors.count() + ", filme: " + films.count());

        System.out.println();
        System.out.println("--- navigare actor->filme ---");
        actors.findAll().forEach(a -> {
            System.out.println("  " + a.getName() + ":");
            a.getFilms().forEach(f -> System.out.println("     - " + f.getTitle()));
        });

        System.out.println();
        System.out.println("--- navigare film->actori (The Matrix) ---");
        films.findAll().stream()
                .filter(f -> f.getTitle().equals("The Matrix"))
                .findFirst()
                .ifPresent(f -> f.getActors().forEach(a -> System.out.println("  " + a.getName())));

        System.out.println();
        System.out.println("--- derived peste relatie: findByFilmsTitle(\"The Matrix\") ---");
        actors.findByFilmsTitle("The Matrix").forEach(a -> System.out.println("  " + a.getName()));

        System.out.println();
        System.out.println("Inspecteaza in DB:  SELECT * FROM actor;  SELECT * FROM film;  SELECT * FROM actor_film;  (tabelul de legatura)");
    }
}
