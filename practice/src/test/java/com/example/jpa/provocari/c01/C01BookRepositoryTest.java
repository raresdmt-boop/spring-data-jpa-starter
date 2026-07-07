package com.example.jpa.provocari.c01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = C01BookRepositoryTest.Config.class)
class C01BookRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Book.class)
    @EnableJpaRepositories(basePackageClasses = BookRepository.class)
    static class Config {
    }

    @Autowired
    private BookRepository repository;

    @Test
    void saveGeneratesIdAndPersists() {
        // Arrange
        Book book = new Book("Ion", 400);

        // Act
        Book saved = repository.save(book);

        // Assert
        assertNotNull(saved.getId());
    }

    @Test
    void findByIdReturnsPersistedBook() {
        // Arrange
        Long id = repository.save(new Book("Enigma Otiliei", 350)).getId();

        // Act
        Book found = repository.findById(id).orElseThrow();

        // Assert
        assertEquals("Enigma Otiliei", found.getTitle());
        assertEquals(350, found.getPages());
    }
}
