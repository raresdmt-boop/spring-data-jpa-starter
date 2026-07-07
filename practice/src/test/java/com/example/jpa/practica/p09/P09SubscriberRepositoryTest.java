package com.example.jpa.practica.p09;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = P09SubscriberRepositoryTest.Config.class)
class P09SubscriberRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Subscriber.class)
    @EnableJpaRepositories(basePackageClasses = SubscriberRepository.class)
    static class Config {
    }

    @Autowired
    private SubscriberRepository repository;

    @Test
    void findByActiveReturnsOnlyActiveSortedByEmail() {
        // Arrange
        repository.save(new Subscriber("zoe@mail.ro", true));
        repository.save(new Subscriber("ana@mail.ro", true));
        repository.save(new Subscriber("ion@mail.ro", false));

        // Act
        List<Subscriber> active = repository.findByActive(true, Sort.by("email").ascending());

        // Assert
        assertEquals(2, active.size());
        assertEquals("ana@mail.ro", active.get(0).getEmail());
    }
}
