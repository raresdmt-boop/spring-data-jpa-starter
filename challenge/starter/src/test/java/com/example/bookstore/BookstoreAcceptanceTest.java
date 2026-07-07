package com.example.bookstore;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class BookstoreAcceptanceTest {

    @Autowired
    private CategoryRepository categories;

    @Autowired
    private BookRepository books;

    @Autowired
    private TestEntityManager entityManager;

    private Long seedProgrammingCategory() {
        Category programming = new Category("Programming");
        programming.addBook(new Book("Clean Code", 180.0));
        programming.addBook(new Book("Effective Java", 220.0));
        programming.addBook(new Book("The Pragmatic Programmer", 150.0));
        Category saved = categories.save(programming);
        entityManager.flush();
        entityManager.clear();
        return saved.getId();
    }

    @Test
    void savingCategoryCascadesItsBooks() {
        // Arrange
        Long categoryId = seedProgrammingCategory();

        // Act
        Category reloaded = categories.findById(categoryId).orElseThrow();

        // Assert
        assertEquals(3, reloaded.getBooks().size());
        assertNotNull(reloaded.getBooks().get(0).getId());
    }

    @Test
    void findByCategoryNameNavigatesTheRelation() {
        // Arrange
        seedProgrammingCategory();

        // Act
        List<Book> found = books.findByCategoryName("Programming");

        // Assert
        assertEquals(3, found.size());
        assertEquals("Programming", found.get(0).getCategory().getName());
    }

    @Test
    void findByPriceLessThanEqualReturnsPagedResult() {
        // Arrange
        seedProgrammingCategory();

        // Act
        Page<Book> page = books.findByPriceLessThanEqual(
                180.0, PageRequest.of(0, 1, Sort.by("price").ascending()));

        // Assert
        assertEquals(2, page.getTotalElements());
        assertEquals(1, page.getContent().size());
        assertEquals("The Pragmatic Programmer", page.getContent().get(0).getTitle());
    }

    @Test
    void findInCategoryMostExpensiveFirstOrdersByPriceDesc() {
        // Arrange
        seedProgrammingCategory();

        // Act
        List<Book> ordered = books.findInCategoryMostExpensiveFirst("Programming");

        // Assert
        assertEquals(3, ordered.size());
        assertEquals("Effective Java", ordered.get(0).getTitle());
        assertEquals("The Pragmatic Programmer", ordered.get(2).getTitle());
    }
}
