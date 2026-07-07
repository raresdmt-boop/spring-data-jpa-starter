package com.example.jpa.ex06;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(classes = Ex06AuthorBookRelationTest.Config.class)
class Ex06AuthorBookRelationTest {

    @Configuration
    @EntityScan(basePackageClasses = Author.class)
    @EnableJpaRepositories(basePackageClasses = AuthorRepository.class)
    static class Config {
    }

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void savingAuthorCascadesBooks() {
        // Arrange
        Author author = new Author("Mihai Eminescu");
        author.addBook(new Book("Poezii"));
        author.addBook(new Book("Proza"));

        // Act
        Author saved = authorRepository.save(author);
        entityManager.flush();
        entityManager.clear();

        // Assert
        Author reloaded = authorRepository.findById(saved.getId()).orElseThrow();
        assertEquals(2, reloaded.getBooks().size());
        assertNotNull(reloaded.getBooks().get(0).getId());
    }

    @Test
    void findByAuthorNameNavigatesTheRelation() {
        // Arrange
        Author author = new Author("Ion Creanga");
        author.addBook(new Book("Amintiri din copilarie"));
        authorRepository.save(author);
        entityManager.flush();
        entityManager.clear();

        // Act
        List<Book> books = bookRepository.findByAuthorName("Ion Creanga");

        // Assert
        assertEquals(1, books.size());
        assertEquals("Amintiri din copilarie", books.get(0).getTitle());
        assertEquals("Ion Creanga", books.get(0).getAuthor().getName());
    }
}
