package com.example.jpa.ex05;

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
@ContextConfiguration(classes = Ex05ArticleRepositoryTest.Config.class)
class Ex05ArticleRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Article.class)
    @EnableJpaRepositories(basePackageClasses = ArticleRepository.class)
    static class Config {
    }

    @Autowired
    private ArticleRepository repository;

    @Test
    void findByCategoryReturnsRequestedPageSortedByViews() {
        // Arrange
        repository.save(new Article("A", "java", 10));
        repository.save(new Article("B", "java", 50));
        repository.save(new Article("C", "java", 30));
        repository.save(new Article("D", "python", 99));

        // Act
        Page<Article> firstPage = repository.findByCategory(
                "java", PageRequest.of(0, 2, Sort.by("views").descending()));

        // Assert
        assertEquals(3, firstPage.getTotalElements());
        assertEquals(2, firstPage.getContent().size());
        assertEquals("B", firstPage.getContent().get(0).getTitle());
        assertEquals("C", firstPage.getContent().get(1).getTitle());
    }

    @Test
    void secondPageContainsRemainingArticle() {
        // Arrange
        repository.save(new Article("A", "java", 10));
        repository.save(new Article("B", "java", 50));
        repository.save(new Article("C", "java", 30));

        // Act
        Page<Article> secondPage = repository.findByCategory(
                "java", PageRequest.of(1, 2, Sort.by("views").descending()));

        // Assert
        assertEquals(2, secondPage.getTotalPages());
        assertEquals(1, secondPage.getContent().size());
        assertEquals("A", secondPage.getContent().get(0).getTitle());
    }
}
