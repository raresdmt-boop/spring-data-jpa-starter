package com.example.jpa.provocari.c06;

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
@ContextConfiguration(classes = C06StudentRepositoryTest.Config.class)
class C06StudentRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Student.class)
    @EnableJpaRepositories(basePackageClasses = StudentRepository.class)
    static class Config {
    }

    @Autowired
    private StudentRepository repository;

    @Test
    void matchesFragmentIgnoringCaseAndOrdersByAge() {
        // Arrange
        repository.save(new Student("Ana Maria", 22));
        repository.save(new Student("MARIA Ionescu", 19));
        repository.save(new Student("Dan Popescu", 25));

        // Act
        List<Student> result = repository.findByNameContainingIgnoreCaseOrderByAgeAsc("maria");

        // Assert
        assertEquals(2, result.size());
        assertEquals("MARIA Ionescu", result.get(0).getName());
    }
}
