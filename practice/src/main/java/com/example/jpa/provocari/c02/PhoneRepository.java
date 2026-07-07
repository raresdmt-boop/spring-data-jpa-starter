package com.example.jpa.provocari.c02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    default List<Phone> findByBrandAndPriceLessThan(String brand, double maxPrice) {
        throw new UnsupportedOperationException("TODO c02");
    }
}
