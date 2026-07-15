package com.example.jpa.practica.p06;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GadgetRepository extends JpaRepository<Gadget, Long> {

    // Cerință: gadgeturile cu prețul între min și max, ordonate crescător după preț.
    // Scrie cu @Query JPQL (`between :min and :max`, `order by`), @Param pe fiecare.
    @Query("select g from Gadget g where g.price >= :min AND g.price <= :max order by price asc")
    List<Gadget> findByPriceRange(@Param("min") double min,@Param("max") double max);

}
