package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void seed() {
        repository.saveAll(List.of(
                new Book("Clean Code", "Robert Martin", "Tech", 180.0, 2008),
                new Book("Effective Java", "Joshua Bloch", "Tech", 220.0, 2018),
                new Book("Dune", "Frank Herbert", "SF", 90.0, 1965),
                new Book("Foundation", "Isaac Asimov", "SF", 75.0, 1951)));
    }

    @Test
    void saveGeneratesId() {
        // Arrange
        Book book = new Book("Refactoring", "Martin Fowler", "Tech", 210.0, 1999);

        // Act
        Book saved = repository.save(book);

        // Assert
        assertNotNull(saved.getId());
    }

    @Test
    void findByGenreReturnsOnlyThatGenre() {
        // Act
        List<Book> sf = repository.findByGenre("SF");

        // Assert
        assertEquals(2, sf.size());
    }

    @Test
    void countByGenreCounts() {
        // Act
        long tech = repository.countByGenre("Tech");

        // Assert
        assertEquals(2, tech);
    }

    @Test
    void findAffordableFiltersAndOrdersAscending() {
        // Act
        List<Book> affordable = repository.findAffordable(100.0);

        // Assert
        assertEquals(2, affordable.size());
        assertEquals("Foundation", affordable.get(0).getTitle());
    }

    @Test
    void averagePriceForGenreComputesAverage() {
        // Act
        Double average = repository.averagePriceForGenre("Tech");

        // Assert
        assertEquals(200.0, average);
    }

    @Test
    void findByGenrePagedReturnsRequestedSlice() {
        // Act
        Page<Book> page = repository.findByGenre("Tech", PageRequest.of(0, 1, Sort.by("price").ascending()));

        // Assert
        assertEquals(2, page.getTotalElements());
        assertEquals(1, page.getContent().size());
        assertEquals("Clean Code", page.getContent().get(0).getTitle());
        assertTrue(page.hasNext());
    }
}
