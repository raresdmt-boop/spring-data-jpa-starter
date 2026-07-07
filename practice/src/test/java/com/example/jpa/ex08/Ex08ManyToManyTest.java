package com.example.jpa.ex08;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = Ex08ManyToManyTest.Config.class)
class Ex08ManyToManyTest {

    @Configuration
    @EntityScan(basePackageClasses = Actor.class)
    @EnableJpaRepositories(basePackageClasses = ActorRepository.class)
    static class Config {
    }

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void actorEnrolledInTwoFilmsIsPersistedOnBothSides() {
        // Arrange
        Actor actor = new Actor("Keanu Reeves");
        actor.addFilm(new Film("The Matrix"));
        actor.addFilm(new Film("John Wick"));
        Actor saved = actorRepository.save(actor);
        entityManager.flush();
        entityManager.clear();

        // Act
        Actor reloaded = actorRepository.findById(saved.getId()).orElseThrow();

        // Assert
        assertEquals(2, reloaded.getFilms().size());
    }

    @Test
    void relationIsVisibleFromTheFilmSide() {
        // Arrange
        Actor actor = new Actor("Carrie-Anne Moss");
        Film matrix = new Film("The Matrix");
        actor.addFilm(matrix);
        actorRepository.save(actor);
        Long filmId = matrix.getId();
        entityManager.flush();
        entityManager.clear();

        // Act
        Film reloaded = filmRepository.findById(filmId).orElseThrow();

        // Assert
        assertEquals(1, reloaded.getActors().size());
    }
}
