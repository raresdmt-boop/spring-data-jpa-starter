package com.example.jpa.provocari.c08;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = C08AlertRepositoryTest.Config.class)
class C08AlertRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Alert.class)
    @EnableJpaRepositories(basePackageClasses = AlertRepository.class)
    static class Config {
    }

    @Autowired
    private AlertRepository repository;

    @Test
    void findFirstByOrderByPriorityDescReturnsHighestPriority() {
        // Arrange
        repository.save(new Alert("disk", 2));
        repository.save(new Alert("cpu", 9));
        repository.save(new Alert("mem", 5));

        // Act
        Alert top = repository.findFirstByOrderByPriorityDesc().orElseThrow();

        // Assert
        assertEquals("cpu", top.getName());
    }
}
