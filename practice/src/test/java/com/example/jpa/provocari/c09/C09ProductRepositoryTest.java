package com.example.jpa.provocari.c09;

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
@ContextConfiguration(classes = C09ProductRepositoryTest.Config.class)
class C09ProductRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Product.class)
    @EnableJpaRepositories(basePackageClasses = ProductRepository.class)
    static class Config {
    }

    @Autowired
    private ProductRepository repository;

    @Test
    void findByCodeStartingWithMatchesPrefix() {
        // Arrange
        repository.save(new Product("EL-100", "Bec LED"));
        repository.save(new Product("EL-220", "Priza"));
        repository.save(new Product("MB-001", "Scaun"));

        // Act
        List<Product> electro = repository.findByCodeStartingWith("EL-");

        // Assert
        assertEquals(2, electro.size());
    }
}
