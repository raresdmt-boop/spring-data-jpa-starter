package com.example.jpa.provocari.c05;

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

@DataJpaTest
@ContextConfiguration(classes = C05CustomerPurchaseTest.Config.class)
class C05CustomerPurchaseTest {

    @Configuration
    @EntityScan(basePackageClasses = Customer.class)
    @EnableJpaRepositories(basePackageClasses = CustomerRepository.class)
    static class Config {
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void savingCustomerCascadesPurchasesAndRelationIsNavigable() {
        // Arrange
        Customer customer = new Customer("Rares");
        customer.addPurchase(new Purchase("Tastatura"));
        customer.addPurchase(new Purchase("Mouse"));
        customerRepository.save(customer);
        entityManager.flush();
        entityManager.clear();

        // Act
        List<Purchase> purchases = purchaseRepository.findByCustomerName("Rares");

        // Assert
        assertEquals(2, purchases.size());
        assertEquals("Rares", purchases.get(0).getCustomer().getName());
    }
}
