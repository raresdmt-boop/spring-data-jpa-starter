package com.example.jpa.ex02;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = Ex02TaskRepositoryTest.Config.class)
class Ex02TaskRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Task.class)
    @EnableJpaRepositories(basePackageClasses = TaskRepository.class)
    static class Config {
    }

    @Autowired
    private TaskRepository repository;

    @Test
    void findByDoneReturnsOnlyMatchingTasks() {
        // Arrange
        repository.save(new Task("Scrie testul", 3, false));
        repository.save(new Task("Trimite mailul", 1, true));

        // Act
        List<Task> done = repository.findByDone(true);

        // Assert
        assertEquals(1, done.size());
        assertEquals("Trimite mailul", done.get(0).getTitle());
    }

    @Test
    void findByPriorityGreaterThanFiltersByThreshold() {
        // Arrange
        repository.save(new Task("Low", 1, false));
        repository.save(new Task("High", 5, false));

        // Act
        List<Task> urgent = repository.findByPriorityGreaterThan(2);

        // Assert
        assertEquals(1, urgent.size());
        assertEquals("High", urgent.get(0).getTitle());
    }

    @Test
    void findByTitleContainingIgnoreCaseIsCaseInsensitive() {
        // Arrange
        repository.save(new Task("Refactor Repository", 2, false));

        // Act
        List<Task> found = repository.findByTitleContainingIgnoreCase("repo");

        // Assert
        assertTrue(found.size() >= 1);
        assertEquals("Refactor Repository", found.get(0).getTitle());
    }
}
