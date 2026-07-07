package com.example.jpa.practica.p08;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // Cerință: cursurile dintr-o categorie, paginat. Metodă derivată pe `category`
    // cu parametru Pageable și retur Page<Course>. Sortarea vine din Pageable.
    default Page<Course> findByCategory(String category, Pageable pageable) {
        throw new UnsupportedOperationException("TODO p08");
    }
}
