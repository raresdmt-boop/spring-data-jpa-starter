package com.example.jpa.provocari.c06;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    default List<Student> findByNameContainingIgnoreCaseOrderByAgeAsc(String fragment) {
        throw new UnsupportedOperationException("TODO c06");
    }
}
