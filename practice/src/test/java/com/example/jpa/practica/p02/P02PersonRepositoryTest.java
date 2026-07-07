package com.example.jpa.practica.p02;

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
@ContextConfiguration(classes = P02PersonRepositoryTest.Config.class)
class P02PersonRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Person.class)
    @EnableJpaRepositories(basePackageClasses = PersonRepository.class)
    static class Config {
    }

    @Autowired
    private PersonRepository repository;

    @Test
    void findByAgeBetweenIsInclusive() {
        // Arrange
        repository.save(new Person("Ana", 18));
        repository.save(new Person("Ion", 25));
        repository.save(new Person("Dan", 40));

        // Act
        List<Person> result = repository.findByAgeBetween(18, 30);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void findByAgeGreaterThanEqualIncludesBoundary() {
        // Arrange
        repository.save(new Person("Ana", 17));
        repository.save(new Person("Ion", 18));

        // Act
        List<Person> adults = repository.findByAgeGreaterThanEqual(18);

        // Assert
        assertEquals(1, adults.size());
        assertEquals("Ion", adults.get(0).getName());
    }
}
