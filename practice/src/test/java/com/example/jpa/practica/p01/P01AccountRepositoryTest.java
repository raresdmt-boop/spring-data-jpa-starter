package com.example.jpa.practica.p01;

import java.util.Optional;

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
@ContextConfiguration(classes = P01AccountRepositoryTest.Config.class)
class P01AccountRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Account.class)
    @EnableJpaRepositories(basePackageClasses = AccountRepository.class)
    static class Config {
    }

    @Autowired
    private AccountRepository repository;

    @Test
    void findByUsernameReturnsTheAccount() {
        // Arrange
        repository.save(new Account("rares", "rares@scoala.ro"));

        // Act
        Optional<Account> found = repository.findByUsername("rares");

        // Assert
        assertTrue(found.isPresent());
        assertEquals("rares@scoala.ro", found.get().getEmail());
    }

    @Test
    void findByUsernameEmptyWhenMissing() {
        // Act
        Optional<Account> found = repository.findByUsername("nimeni");

        // Assert
        assertTrue(found.isEmpty());
    }
}
