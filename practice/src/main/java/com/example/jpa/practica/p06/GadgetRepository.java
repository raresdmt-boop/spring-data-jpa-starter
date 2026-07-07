package com.example.jpa.practica.p06;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GadgetRepository extends JpaRepository<Gadget, Long> {

    // Cerință: gadgeturile cu prețul între min și max, ordonate crescător după preț.
    // Scrie cu @Query JPQL (`between :min and :max`, `order by`), @Param pe fiecare.
    default List<Gadget> findByPriceRange(double min, double max) {
        throw new UnsupportedOperationException("TODO p06");
    }
}
