package com.example.jpa.provocari.c05;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    default List<Purchase> findByCustomerName(String name) {
        throw new UnsupportedOperationException("TODO c05");
    }
}
