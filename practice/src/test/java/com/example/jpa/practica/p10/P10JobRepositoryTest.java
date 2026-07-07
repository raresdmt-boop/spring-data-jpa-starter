package com.example.jpa.practica.p10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = P10JobRepositoryTest.Config.class)
class P10JobRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Job.class)
    @EnableJpaRepositories(basePackageClasses = JobRepository.class)
    static class Config {
    }

    @Autowired
    private JobRepository repository;

    @Test
    void deleteByStatusRemovesMatchingAndReturnsCount() {
        // Arrange
        repository.save(new Job("import", "DONE"));
        repository.save(new Job("export", "DONE"));
        repository.save(new Job("sync", "RUNNING"));

        // Act
        long deleted = repository.deleteByStatus("DONE");

        // Assert
        assertEquals(2, deleted);
        assertEquals(1, repository.count());
    }
}
