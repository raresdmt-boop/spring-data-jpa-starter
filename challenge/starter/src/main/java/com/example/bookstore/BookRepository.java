package com.example.bookstore;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    // TODO challenge.3: cărțile dintr-o categorie (derived query care navighează
    //   relația Book -> category -> name). Șterge `default` + corpul, lasă semnătura.
    default List<Book> findByCategoryName(String name) {
        throw new UnsupportedOperationException("TODO challenge.3: findByCategoryName");
    }

    // TODO challenge.4: cărțile cu preț <= maxPrice, PAGINAT (parametru Pageable,
    //   retur Page<Book>). Cuvânt cheie derivat: `PriceLessThanEqual`.
    default Page<Book> findByPriceLessThanEqual(double maxPrice, Pageable pageable) {
        throw new UnsupportedOperationException("TODO challenge.4: findByPriceLessThanEqual paginat");
    }

    // TODO challenge.5: @Query JPQL — cărțile dintr-o categorie, ordonate
    //   descrescător după preț (cel mai scump primul). Folosește @Param.
    default List<Book> findInCategoryMostExpensiveFirst(String name) {
        throw new UnsupportedOperationException("TODO challenge.5: @Query findInCategoryMostExpensiveFirst");
    }
}
