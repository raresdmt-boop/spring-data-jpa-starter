package com.example.jpa.ex04;

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
@ContextConfiguration(classes = Ex04MovieRepositoryTest.Config.class)
class Ex04MovieRepositoryTest {

    @Configuration
    @EntityScan(basePackageClasses = Movie.class)
    @EnableJpaRepositories(basePackageClasses = MovieRepository.class)
    static class Config {
    }

    @Autowired
    private MovieRepository repository;

    @Test
    void findGoodMoviesByGenreFiltersAndOrders() {
        // Arrange
        repository.save(new Movie("Inception", "SF", 2010, 8.8));
        repository.save(new Movie("Tenet", "SF", 2020, 7.4));
        repository.save(new Movie("Weak SF", "SF", 2000, 5.0));
        repository.save(new Movie("Comedy One", "Comedy", 2015, 9.0));

        // Act
        List<Movie> good = repository.findGoodMoviesByGenre("SF", 7.0);

        // Assert
        assertEquals(2, good.size());
        assertEquals("Inception", good.get(0).getTitle());
    }

    @Test
    void averageRatingForGenreComputesAverage() {
        // Arrange
        repository.save(new Movie("A", "Drama", 2001, 6.0));
        repository.save(new Movie("B", "Drama", 2002, 8.0));

        // Act
        Double average = repository.averageRatingForGenre("Drama");

        // Assert
        assertEquals(7.0, average);
    }
}
