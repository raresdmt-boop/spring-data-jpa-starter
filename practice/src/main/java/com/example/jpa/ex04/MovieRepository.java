package com.example.jpa.ex04;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Aici scrii tu JPQL-ul cu @Query (org.springframework.data.jpa.repository.Query)
// și legi parametrii cu @Param (org.springframework.data.repository.query.Param).
// Pentru fiecare TODO: adaugă adnotarea @Query, șterge `default` + corpul și
// lasă semnătura abstractă cu @Param pe fiecare argument.
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // TODO ex04.1: @Query JPQL care selectează filmele cu genre = :genre ȘI
    //   rating >= :minRating, ordonate descrescător după rating.
    //   Atenție: în JPQL folosești numele câmpurilor din entitate, nu coloanele SQL.
    default List<Movie> findGoodMoviesByGenre(String genre, double minRating) {
        throw new UnsupportedOperationException("TODO ex04.1: @Query findGoodMoviesByGenre");
    }

    // TODO ex04.2: @Query JPQL cu funcția de agregare avg(...) care întoarce media
    //   rating-urilor pentru un gen dat. Tipul de retur este Double.
    default Double averageRatingForGenre(String genre) {
        throw new UnsupportedOperationException("TODO ex04.2: @Query averageRatingForGenre");
    }
}
