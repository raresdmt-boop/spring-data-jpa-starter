package com.example.jpa.ex03;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = Ex03EmployeeRepositoryTest.Config.class)
class Ex03EmployeeRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Employee.class)
    @EnableJpaRepositories(basePackageClasses = EmployeeRepository.class)
    static class Config {
    }

    @Autowired
    private EmployeeRepository repository;

    @Test
    void findByDepartmentOrderBySalaryDescSortsHighestFirst() {
        // Arrange
        repository.save(new Employee("Ana", "Pop", "IT", 6000, true, "ana@firma.ro"));
        repository.save(new Employee("Ion", "Ene", "IT", 9000, true, "ion@firma.ro"));
        repository.save(new Employee("Dan", "Roman", "HR", 5000, true, "dan@firma.ro"));

        // Act
        List<Employee> it = repository.findByDepartmentOrderBySalaryDesc("IT");

        // Assert
        assertEquals(2, it.size());
        assertEquals("Ene", it.get(0).getLastName());
    }

    @Test
    void countByActiveTrueCountsOnlyActive() {
        // Arrange
        repository.save(new Employee("A", "Unu", "IT", 100, true, "a@firma.ro"));
        repository.save(new Employee("B", "Doi", "IT", 100, false, "b@firma.ro"));

        // Act
        long activeCount = repository.countByActiveTrue();

        // Assert
        assertEquals(1, activeCount);
    }

    @Test
    void existsByEmailReflectsPresence() {
        // Arrange
        repository.save(new Employee("Ana", "Pop", "IT", 6000, true, "ana@firma.ro"));

        // Act
        boolean exists = repository.existsByEmail("ana@firma.ro");
        boolean missing = repository.existsByEmail("nimeni@firma.ro");

        // Assert
        assertTrue(exists);
        assertFalse(missing);
    }

    @Test
    void findByLastNameContainingIgnoreCaseMatchesFragment() {
        // Arrange
        repository.save(new Employee("Ion", "Popescu", "IT", 6000, true, "ion@firma.ro"));

        // Act
        List<Employee> found = repository.findByLastNameContainingIgnoreCase("pop");

        // Assert
        assertEquals(1, found.size());
        assertEquals("Popescu", found.get(0).getLastName());
    }
}
