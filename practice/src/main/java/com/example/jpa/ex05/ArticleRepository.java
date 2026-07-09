package com.example.jpa.ex05;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Paginare + sortare: adaugi un parametru Pageable la o metodă derivată și
// întorci Page<T>. Sortarea vine din Pageable (Sort), nu din numele metodei.
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // TODO ex05.1: articolele dintr-o categorie, paginat.
    //   Metodă derivată pe `category`, cu parametru Pageable și retur Page<Article>.
    //   -> Page<Article> findByCategory(String category, Pageable pageable);
    //   Șterge `default` + corpul și lasă doar semnătura abstractă.

    Page<Article> findByCategory(String category, Pageable pageable);
//
//    default Page<Article> findByCategory(String category, Pageable pageable) {
//        throw new UnsupportedOperationException("TODO ex05.1: findByCategory paginat");
//    }
}
