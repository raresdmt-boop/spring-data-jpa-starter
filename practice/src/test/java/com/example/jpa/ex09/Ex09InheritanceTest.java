package com.example.jpa.ex09;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = Ex09InheritanceTest.Config.class)
class Ex09InheritanceTest {

    @Configuration
    @EntityScan(basePackageClasses = Payment.class)
    @EnableJpaRepositories(basePackageClasses = PaymentRepository.class)
    static class Config {
    }

    @Autowired
    private PaymentRepository repository;

    @Test
    void differentSubtypesArePersistedInOneTable() {
        // Arrange
        repository.save(new CardPayment(150.0, "1234-5678"));
        repository.save(new CashPayment(40.0));

        // Act
        long total = repository.count();

        // Assert
        assertEquals(2, total);
    }

    @Test
    void findAllReturnsPolymorphicResults() {
        // Arrange
        repository.save(new CardPayment(150.0, "1234-5678"));
        repository.save(new CashPayment(40.0));

        // Act
        List<Payment> payments = repository.findAll();

        // Assert
        boolean hasCard = payments.stream().anyMatch(p -> p instanceof CardPayment);
        boolean hasCash = payments.stream().anyMatch(p -> p instanceof CashPayment);
        assertTrue(hasCard);
        assertTrue(hasCash);
    }
}
