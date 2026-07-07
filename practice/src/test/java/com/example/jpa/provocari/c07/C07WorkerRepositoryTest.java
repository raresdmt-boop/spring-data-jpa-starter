package com.example.jpa.provocari.c07;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = C07WorkerRepositoryTest.Config.class)
class C07WorkerRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Worker.class)
    @EnableJpaRepositories(basePackageClasses = WorkerRepository.class)
    static class Config {
    }

    @Autowired
    private WorkerRepository repository;

    @Test
    void averageSalaryComputesPerDepartment() {
        // Arrange
        repository.save(new Worker("IT", 6000));
        repository.save(new Worker("IT", 8000));
        repository.save(new Worker("HR", 4000));

        // Act
        Double avgIt = repository.averageSalary("IT");

        // Assert
        assertEquals(7000.0, avgIt);
    }
}
