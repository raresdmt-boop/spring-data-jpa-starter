package com.example.jpa.provocari.c02;

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
@ContextConfiguration(classes = C02PhoneRepositoryTest.Config.class)
class C02PhoneRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Phone.class)
    @EnableJpaRepositories(basePackageClasses = PhoneRepository.class)
    static class Config {
    }

    @Autowired
    private PhoneRepository repository;

    @Test
    void findByBrandAndPriceLessThanCombinesBothConditions() {
        // Arrange
        repository.save(new Phone("Samsung", 1500.0));
        repository.save(new Phone("Samsung", 900.0));
        repository.save(new Phone("Apple", 800.0));

        // Act
        List<Phone> cheapSamsung = repository.findByBrandAndPriceLessThan("Samsung", 1000.0);

        // Assert
        assertEquals(1, cheapSamsung.size());
        assertEquals(900.0, cheapSamsung.get(0).getPrice());
    }
}
