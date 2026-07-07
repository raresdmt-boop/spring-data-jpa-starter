package com.example.jpa.ex07;

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
@ContextConfiguration(classes = Ex07OneToOneTest.Config.class)
class Ex07OneToOneTest {

    @Configuration
    @EntityScan(basePackageClasses = Citizen.class)
    @EnableJpaRepositories(basePackageClasses = CitizenRepository.class)
    static class Config {
    }

    @Autowired
    private CitizenRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void savingCitizenCascadesPassportAndRelationIsNavigable() {
        // Arrange
        Citizen citizen = new Citizen("Ana");
        citizen.setPassport(new Passport("RO-123456"));

        // Act
        Citizen saved = repository.save(citizen);
        entityManager.flush();
        entityManager.clear();

        // Assert
        Citizen reloaded = repository.findById(saved.getId()).orElseThrow();
        assertNotNull(reloaded.getPassport().getId());
        assertEquals("RO-123456", reloaded.getPassport().getCode());
    }
}
