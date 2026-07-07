package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(String genre);

    List<Book> findByAuthorContainingIgnoreCase(String fragment);

    long countByGenre(String genre);

    @Query("select b from Book b where b.price <= :max order by b.price asc")
    List<Book> findAffordable(@Param("max") double max);

    @Query("select avg(b.price) from Book b where b.genre = :genre")
    Double averagePriceForGenre(@Param("genre") String genre);

    Page<Book> findByGenre(String genre, Pageable pageable);
}
