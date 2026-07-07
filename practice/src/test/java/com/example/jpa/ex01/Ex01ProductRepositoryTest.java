package com.example.jpa.ex01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = Ex01ProductRepositoryTest.Config.class)
class Ex01ProductRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Product.class)
    @EnableJpaRepositories(basePackageClasses = ProductRepository.class)
    static class Config {
    }

    @Autowired
    private ProductRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void savePersistsProductAndGeneratesId() {
        // Arrange
        Product product = new Product("Laptop", 4200.0);

        // Act
        Product saved = repository.save(product);

        // Assert
        assertNotNull(saved.getId());
    }

    @Test
    void findByIdReturnsSavedProduct() {
        // Arrange
        Product persisted = entityManager.persistFlushFind(new Product("Mouse", 99.0));

        // Act
        Product found = repository.findById(persisted.getId()).orElseThrow();

        // Assert
        assertEquals("Mouse", found.getName());
        assertEquals(99.0, found.getPrice());
    }

    @Test
    void countReflectsNumberOfSavedProducts() {
        // Arrange
        repository.save(new Product("Keyboard", 199.0));
        repository.save(new Product("Monitor", 899.0));

        // Act
        long total = repository.count();

        // Assert
        assertTrue(total >= 2);
    }
}
