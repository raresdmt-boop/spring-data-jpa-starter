package com.example.jpa.practica.p07;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = P07ItemRepositoryTest.Config.class)
class P07ItemRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Item.class)
    @EnableJpaRepositories(basePackageClasses = ItemRepository.class)
    static class Config {
    }

    @Autowired
    private ItemRepository repository;

    @Test
    void totalStockSumsAllItems() {
        // Arrange
        repository.save(new Item("Suruburi", 100));
        repository.save(new Item("Piulite", 250));
        repository.save(new Item("Saibe", 50));

        // Act
        Long total = repository.totalStock();

        // Assert
        assertEquals(400L, total);
    }
}
