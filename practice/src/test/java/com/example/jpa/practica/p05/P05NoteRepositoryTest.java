package com.example.jpa.practica.p05;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = P05NoteRepositoryTest.Config.class)
class P05NoteRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Note.class)
    @EnableJpaRepositories(basePackageClasses = NoteRepository.class)
    static class Config {
    }

    @Autowired
    private NoteRepository repository;

    @Test
    void findInCategoryReturnsOnlyThatCategory() {
        // Arrange
        repository.save(new Note("Idei proiect", "work"));
        repository.save(new Note("Lista cumparaturi", "home"));
        repository.save(new Note("Retrospectiva", "work"));

        // Act
        List<Note> work = repository.findInCategory("work");

        // Assert
        assertEquals(2, work.size());
    }
}
