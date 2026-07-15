package com.example.jpa.provocari.c09;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCodeStartingWith(String prefix);
}
