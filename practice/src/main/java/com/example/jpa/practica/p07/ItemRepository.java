package com.example.jpa.practica.p07;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Cerință: suma totală a stocului tuturor articolelor. Folosește @Query cu
    // funcția de agregare sum(...); tipul de retur e Long.
    default Long totalStock() {
        throw new UnsupportedOperationException("TODO p07");
    }
}
