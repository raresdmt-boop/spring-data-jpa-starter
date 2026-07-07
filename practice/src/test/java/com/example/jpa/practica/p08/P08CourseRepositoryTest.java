package com.example.jpa.practica.p08;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = P08CourseRepositoryTest.Config.class)
class P08CourseRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Course.class)
    @EnableJpaRepositories(basePackageClasses = CourseRepository.class)
    static class Config {
    }

    @Autowired
    private CourseRepository repository;

    @Test
    void findByCategoryReturnsFirstPageSortedByEnrolledDesc() {
        // Arrange
        repository.save(new Course("Java", "backend", 30));
        repository.save(new Course("Spring", "backend", 50));
        repository.save(new Course("SQL", "backend", 40));
        repository.save(new Course("React", "frontend", 99));

        // Act
        Page<Course> page = repository.findByCategory(
                "backend", PageRequest.of(0, 2, Sort.by("enrolled").descending()));

        // Assert
        assertEquals(3, page.getTotalElements());
        assertEquals(2, page.getContent().size());
        assertEquals("Spring", page.getContent().get(0).getTitle());
    }
}
