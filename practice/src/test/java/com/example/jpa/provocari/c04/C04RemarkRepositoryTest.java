package com.example.jpa.provocari.c04;

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
@ContextConfiguration(classes = C04RemarkRepositoryTest.Config.class)
class C04RemarkRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Remark.class)
    @EnableJpaRepositories(basePackageClasses = RemarkRepository.class)
    static class Config {
    }

    @Autowired
    private RemarkRepository repository;

    @Test
    void secondPageOfPopularRemarksSortedByLikesDesc() {
        // Arrange
        repository.save(new Remark("a", 5));
        repository.save(new Remark("b", 50));
        repository.save(new Remark("c", 30));
        repository.save(new Remark("d", 20));
        repository.save(new Remark("e", 1));

        // Act
        Page<Remark> secondPage = repository.findByLikesGreaterThan(
                10, PageRequest.of(1, 2, Sort.by("likes").descending()));

        // Assert
        assertEquals(3, secondPage.getTotalElements());
        assertEquals(1, secondPage.getContent().size());
        assertEquals("d", secondPage.getContent().get(0).getBody());
    }
}
