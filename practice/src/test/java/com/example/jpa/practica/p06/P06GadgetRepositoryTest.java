package com.example.jpa.practica.p06;

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
@ContextConfiguration(classes = P06GadgetRepositoryTest.Config.class)
class P06GadgetRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Gadget.class)
    @EnableJpaRepositories(basePackageClasses = GadgetRepository.class)
    static class Config {
    }

    @Autowired
    private GadgetRepository repository;

    @Test
    void findByPriceRangeFiltersAndOrders() {
        // Arrange
        repository.save(new Gadget("Cablu", 20.0));
        repository.save(new Gadget("Casti", 150.0));
        repository.save(new Gadget("Tastatura", 250.0));
        repository.save(new Gadget("Monitor", 900.0));

        // Act
        List<Gadget> midRange = repository.findByPriceRange(100.0, 300.0);

        // Assert
        assertEquals(2, midRange.size());
        assertEquals("Casti", midRange.get(0).getName());
    }
}
