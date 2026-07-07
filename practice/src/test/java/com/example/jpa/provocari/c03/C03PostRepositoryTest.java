package com.example.jpa.provocari.c03;

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
@ContextConfiguration(classes = C03PostRepositoryTest.Config.class)
class C03PostRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Post.class)
    @EnableJpaRepositories(basePackageClasses = PostRepository.class)
    static class Config {
    }

    @Autowired
    private PostRepository repository;

    @Test
    void searchByKeywordMatchesAnywhereCaseInsensitive() {
        // Arrange
        repository.save(new Post("Introducere in Spring Boot"));
        repository.save(new Post("SPRING Data JPA pe scurt"));
        repository.save(new Post("Docker pentru incepatori"));

        // Act
        List<Post> found = repository.searchByKeyword("spring");

        // Assert
        assertEquals(2, found.size());
    }
}
