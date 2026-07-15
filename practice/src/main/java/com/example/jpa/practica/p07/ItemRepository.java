package com.example.jpa.practica.p07;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Cerință: suma totală a stocului tuturor articolelor. Folosește @Query cu
    // funcția de agregare sum(...); tipul de retur e Long.
    @Query("select sum(i.stock) from Item i")
    Long totalStock();

}
