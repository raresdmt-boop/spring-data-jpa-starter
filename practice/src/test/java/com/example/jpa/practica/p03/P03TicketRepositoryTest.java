package com.example.jpa.practica.p03;

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
@ContextConfiguration(classes = P03TicketRepositoryTest.Config.class)
class P03TicketRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Ticket.class)
    @EnableJpaRepositories(basePackageClasses = TicketRepository.class)
    static class Config {
    }

    @Autowired
    private TicketRepository repository;

    @Test
    void findByStatusInReturnsAllMatchingStatuses() {
        // Arrange
        repository.save(new Ticket("Login broken", "OPEN"));
        repository.save(new Ticket("Slow page", "IN_PROGRESS"));
        repository.save(new Ticket("Typo", "CLOSED"));

        // Act
        List<Ticket> active = repository.findByStatusIn(List.of("OPEN", "IN_PROGRESS"));

        // Assert
        assertEquals(2, active.size());
    }

    @Test
    void countByStatusCountsOneStatus() {
        // Arrange
        repository.save(new Ticket("A", "OPEN"));
        repository.save(new Ticket("B", "OPEN"));
        repository.save(new Ticket("C", "CLOSED"));

        // Act
        long open = repository.countByStatus("OPEN");

        // Assert
        assertEquals(2, open);
    }
}
