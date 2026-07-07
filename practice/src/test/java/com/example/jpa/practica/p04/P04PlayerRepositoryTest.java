package com.example.jpa.practica.p04;

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
@ContextConfiguration(classes = P04PlayerRepositoryTest.Config.class)
class P04PlayerRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Player.class)
    @EnableJpaRepositories(basePackageClasses = PlayerRepository.class)
    static class Config {
    }

    @Autowired
    private PlayerRepository repository;

    @Test
    void findFirstByOrderByPointsDescReturnsTopScorer() {
        // Arrange
        repository.save(new Player("Ana", 120));
        repository.save(new Player("Ion", 300));
        repository.save(new Player("Dan", 90));

        // Act
        Player top = repository.findFirstByOrderByPointsDesc().orElseThrow();

        // Assert
        assertEquals("Ion", top.getName());
    }

    @Test
    void findTop3ByOrderByPointsDescLimitsToThree() {
        // Arrange
        repository.save(new Player("A", 10));
        repository.save(new Player("B", 20));
        repository.save(new Player("C", 30));
        repository.save(new Player("D", 40));

        // Act
        List<Player> top3 = repository.findTop3ByOrderByPointsDesc();

        // Assert
        assertEquals(3, top3.size());
        assertEquals("D", top3.get(0).getName());
    }
}
